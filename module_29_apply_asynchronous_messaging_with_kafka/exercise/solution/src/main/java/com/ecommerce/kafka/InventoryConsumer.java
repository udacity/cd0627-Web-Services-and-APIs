package com.ecommerce.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;
import org.springframework.dao.TransientDataAccessException;

@Component
public class InventoryConsumer {

    @RetryableTopic(
        attempts = "3", 
        backoff = @Backoff(delay = 1000, multiplier = 2),
        exclude = MalformedOrderException.class
    )
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
