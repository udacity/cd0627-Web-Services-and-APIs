package com.ecommerce.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RetryableTopic(attempts = "3")
    @KafkaListener(topics = "product-views", groupId = "analytics-group")
    public void listen(ProductViewedEvent event) {
        System.out.println("Received ProductViewedEvent: " + event);
        if (event.productId() == null) {
            System.err.println("Error: productId is null. Throwing exception...");
            throw new RuntimeException("productId cannot be null");
        }
        System.out.println("Successfully processed event.");
    }
}
