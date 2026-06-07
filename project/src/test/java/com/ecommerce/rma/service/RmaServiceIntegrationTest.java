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

    // =========================================================================
    // TODO (Integration Test – Part A: Service Injection):
    //
    // 1. Inject DashboardService to read the CQRS read-model in your assertions:
    //      @Autowired
    //      private DashboardService dashboardService;
    //
    // 2. Inject KafkaTemplate to publish test events directly (bypassing the AI):
    //      @Autowired
    //      private KafkaTemplate<String, Object> kafkaTemplate;
    //
    // 3. Mock RmaService using @MockitoBean so no real OpenAI key is needed.
    //    The mock stub will publish the Kafka event manually:
    //      @MockitoBean
    //      private RmaService rmaService;
    // =========================================================================

    // TODO (Part A): Uncomment and complete the fields below:
    // @Autowired
    // private DashboardService dashboardService;

    // @Autowired
    // private KafkaTemplate<String, Object> kafkaTemplate;

    // @MockitoBean
    // private RmaService rmaService;


    // =========================================================================
    // TODO (Integration Test – Part B: Test Method):
    //
    // Implement the test below. The test should:
    //
    //  Step 1 – Stub RmaService so it:
    //    a) Publishes a ReturnApprovedEvent to Kafka (simulating Step 5 of the real service).
    //    b) Returns a ReturnResponse with approved=true.
    //
    //  Stubbing example using doAnswer (so we can call kafkaTemplate inside the mock):
    //
    //    doAnswer(invocation -> {
    //        ReturnRequest req = invocation.getArgument(0);
    //        var event = new ReturnApprovedEvent(
    //                req.customerId(), "electronics", "Defective item – approved.");
    //        kafkaTemplate.send("returns-topic", req.customerId(), event);
    //        return new ReturnResponse(true, "Defective item – approved.");
    //    }).when(rmaService).processReturn(any(ReturnRequest.class));
    //
    //  Step 2 – Create a ReturnRequest and call the (mocked) service:
    //    ReturnRequest request = new ReturnRequest("INTEG-001",
    //            "My laptop screen cracked after one week of normal use.");
    //    ReturnResponse response = rmaService.processReturn(request);
    //    assertTrue(response.approved());
    //
    //  Step 3 – Wait for DashboardService to consume the Kafka event.
    //  Because Kafka delivery is asynchronous, use Awaitility to poll:
    //
    //    await().atMost(Duration.ofSeconds(10)).until(() ->
    //        dashboardService.getDashboard().stream()
    //            .anyMatch(e -> "INTEG-001".equals(e.customerId()))
    //    );
    //
    //  Step 4 – Assert the dashboard has the entry:
    //    Collection<ReturnApprovedEvent> dashboard = dashboardService.getDashboard();
    //    assertFalse(dashboard.isEmpty());
    //    assertTrue(dashboard.stream().anyMatch(e -> "INTEG-001".equals(e.customerId())));
    //
    // Key insight: Awaitility is the standard way to test async systems. It repeatedly
    // calls the lambda until it returns true or the timeout expires — avoiding
    // brittle Thread.sleep() calls.
    // =========================================================================

    @Test
    void testFullFlow_approvedReturnAppearsInDashboard() {
        // TODO (Part B): Implement this integration test.
        // Remove the line below once you have implemented the test.
        System.out.println("[RmaServiceIntegrationTest] Not yet implemented – complete TODOs A and B.");

        // Reminder of the steps (uncomment and fill in as you go):

        // --- Step 1: Stub RmaService ---
        // doAnswer(invocation -> { ... }).when(rmaService).processReturn(any());

        // --- Step 2: Call the service ---
        // ReturnRequest request = new ReturnRequest("INTEG-001", "Laptop screen cracked.");
        // ReturnResponse response = rmaService.processReturn(request);
        // assertTrue(response.approved(), "Return should be approved");

        // --- Step 3: Wait for async Kafka consumer ---
        // await().atMost(Duration.ofSeconds(10)).until(() ->
        //     dashboardService.getDashboard().stream()
        //         .anyMatch(e -> "INTEG-001".equals(e.customerId()))
        // );

        // --- Step 4: Assert dashboard contains the entry ---
        // Collection<ReturnApprovedEvent> dashboard = dashboardService.getDashboard();
        // assertFalse(dashboard.isEmpty(), "Dashboard should not be empty");
        // assertTrue(dashboard.stream().anyMatch(e -> "INTEG-001".equals(e.customerId())),
        //         "Dashboard should contain the entry for INTEG-001");
    }
}
