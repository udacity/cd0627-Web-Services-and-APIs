package com.ecommerce.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.dao.TransientDataAccessException;

@Component
public class InventoryConsumer {

    // TODO: Add @RetryableTopic here with 3 attempts.
    // TODO: Configure exponential @Backoff (e.g. wait 1s, then 2s, then 4s).
    // TODO: Exclude MalformedOrderException.class from retries entirely.
    @KafkaListener(topics = "order-events", groupId = "inventory-group")
    public void consume(OrderPlacedEvent event) {
        System.out.println("InventoryConsumer received: " + event.orderId());
        
        if (event.payload() == null) {
            System.err.println("InventoryConsumer: Malformed payload!");
            throw new MalformedOrderException("Missing payload for order " + event.orderId());
        }
        
        if ("ORD-LOCK".equals(event.orderId())) {
            System.err.println("InventoryConsumer: Database lock encountered!");
            throw new TransientDataAccessException("DB Lock") {};
        }
        
        System.out.println("InventoryConsumer: Successfully updated inventory for " + event.orderId());
    }
}
