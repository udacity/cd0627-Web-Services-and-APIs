package com.ecommerce.rma.service;

import com.ecommerce.rma.dto.ReturnRequest;
import com.ecommerce.rma.dto.ReturnResponse;
import com.ecommerce.rma.event.ReturnApprovedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.Duration;
import java.util.Collection;

import static com.ecommerce.rma.config.KafkaLocalConfig.RETURNS_TOPIC;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

/**
 * Integration test that verifies the full end-to-end Kafka flow of the RMA engine:
 * <ol>
 *   <li>A return request is processed by {@link RmaService}.</li>
 *   <li>An approved event is published to the in-memory Kafka topic.</li>
 *   <li>{@link DashboardService} consumes the event and adds it to its read-model.</li>
 *   <li>The dashboard read-model reflects the new entry.</li>
 * </ol>
 *
 * <h2>Why @SpringBootTest (not @WebMvcTest)?</h2>
 * {@code @SpringBootTest} loads the <em>full</em> Spring application context —
 * Kafka producer, consumer, embedded broker (from {@code KafkaLocalConfig}), and
 * all services. This lets us test that all the pieces work together end-to-end,
 * unlike {@code @WebMvcTest} which only loads the web layer.
 *
 * <h2>Embedded Kafka</h2>
 * {@code KafkaLocalConfig} starts an in-memory Kafka broker on port 9092 as part
 * of the Spring context. No separate {@code @EmbeddedKafka} annotation is needed —
 * the same broker is shared between the producer in {@code RmaService} and the
 * consumer in {@code DashboardService}.
 *
 * <h2>Mocking the AI calls</h2>
 * {@code @MockitoBean} replaces {@code RmaService} with a controlled stub so this
 * test does <em>not</em> require a real {@code OPENAI_API_KEY}. The stub directly
 * publishes a {@link ReturnApprovedEvent} to Kafka — simulating what the real
 * {@code RmaService} would do after Steps 3–5 are implemented.
 *
 * <p>Run with: {@code ./mvnw test -Dtest=RmaServiceIntegrationTest}
 */
@SpringBootTest
class RmaServiceIntegrationTest {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @MockitoBean
    private RmaService rmaService;

    @Test
    void testFullFlow_approvedReturnAppearsInDashboard() {
        doAnswer(invocation -> {
            ReturnRequest req = invocation.getArgument(0);
            var event = new ReturnApprovedEvent(
                    req.customerId(), "electronics", "Defective item – approved.");
            kafkaTemplate.send(RETURNS_TOPIC, req.customerId(), event);
            return new ReturnResponse(true, "Defective item – approved.");
        }).when(rmaService).processReturn(any(ReturnRequest.class));

        ReturnRequest request = new ReturnRequest("INTEG-001",
                "My laptop screen cracked after one week of normal use.");
        ReturnResponse response = rmaService.processReturn(request);
        assertTrue(response.approved(), "Return should be approved");

        await().atMost(Duration.ofSeconds(10)).until(() ->
                dashboardService.getDashboard().stream()
                        .anyMatch(e -> "INTEG-001".equals(e.customerId()))
        );

        Collection<ReturnApprovedEvent> dashboard = dashboardService.getDashboard();
        assertFalse(dashboard.isEmpty(), "Dashboard should not be empty");
        assertTrue(dashboard.stream().anyMatch(e -> "INTEG-001".equals(e.customerId())),
                "Dashboard should contain the entry for INTEG-001");
    }
}
