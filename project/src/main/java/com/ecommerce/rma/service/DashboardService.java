package com.ecommerce.rma.service;

import com.ecommerce.rma.event.ReturnApprovedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.ecommerce.rma.config.KafkaLocalConfig.RETURNS_TOPIC;

/**
 * CQRS read-model service for the RMA dashboard.
 *
 * <p>This service is the <b>consumer</b> side of the event pipeline. It listens on
 * the {@code "returns-topic"} Kafka topic, receives {@link ReturnApprovedEvent}
 * messages, and maintains an in-memory map of approved returns that can be
 * queried via GraphQL.
 *
 * <h2>CQRS Pattern</h2>
 * <ul>
 *   <li><b>Command side (write):</b> {@code RmaService} processes the return and
 *       publishes events to Kafka.</li>
 *   <li><b>Query side (read):</b> {@code DashboardService} consumes those events
 *       and maintains the {@code dashboardView} map used by the GraphQL query.</li>
 * </ul>
 * This separation means the dashboard can be queried at any time without touching
 * the write path, and the read-model can be rebuilt by replaying events.
 */
@Service
public class DashboardService {

    /**
     * In-memory read-model: maps {@code customerId} → the latest
     * {@link ReturnApprovedEvent} for that customer.
     *
     * <p>{@link ConcurrentHashMap} is used because events may arrive on a Kafka
     * listener thread while the GraphQL query reads on the HTTP thread.
     */
    private final Map<String, ReturnApprovedEvent> dashboardView = new ConcurrentHashMap<>();

    // =========================================================================
    // TODO (Step 6 – Kafka Consumer / @KafkaListener):
    //
    // Goal: Consume ReturnApprovedEvent messages from Kafka and store them in
    //       the dashboardView map so they can be queried via GraphQL.
    //
    // Instructions:
    //  1. Add a @KafkaListener annotation above the method:
    //       @KafkaListener(topics = RETURNS_TOPIC, groupId = "dashboard-group")
    //     - `topics` must match the topic name the producer sends to.
    //     - `groupId` identifies this consumer group (separate from "rma-group"
    //       used by other potential consumers of the same topic).
    //
    //  2. The method parameter must be ReturnApprovedEvent — Spring Kafka
    //     automatically deserialises the JSON payload using Jackson:
    //       public void onReturnApproved(ReturnApprovedEvent event) { ... }
    //
    //  3. Inside the method, store the event in the map keyed by customerId:
    //       dashboardView.put(event.customerId(), event);
    //
    //  4. Optionally log the received event:
    //       System.out.println("[DashboardService] Received event: " + event);
    //
    // Full example:
    //
    //   @KafkaListener(topics = RETURNS_TOPIC, groupId = "dashboard-group")
    //   public void onReturnApproved(ReturnApprovedEvent event) {
    //       dashboardView.put(event.customerId(), event);
    //       System.out.println("[DashboardService] Stored approved return: " + event);
    //   }
    //
    // After adding this method, submit an approved return via POST /api/returns,
    // then query the GraphQL dashboard to see it appear.
    // =========================================================================

    @KafkaListener(topics = RETURNS_TOPIC, groupId = "dashboard-group")
    public void onReturnApproved(ReturnApprovedEvent event) {
        dashboardView.put(event.customerId(), event);
        System.out.println("[DashboardService] Stored approved return: " + event);
    }


    /**
     * Returns all currently approved return events stored in the read-model.
     * Called by {@link com.ecommerce.rma.controller.DashboardController} to
     * serve the GraphQL {@code returnsDashboard} query.
     *
     * @return a snapshot of all approved return events
     */
    public Collection<ReturnApprovedEvent> getDashboard() {
        return dashboardView.values();
    }
}