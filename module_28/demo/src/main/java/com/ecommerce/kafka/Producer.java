package com.ecommerce.kafka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer implements CommandLineRunner {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public Producer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void run(String... args) {
        System.out.println("Pushing ProductViewedEvent to Kafka...");
        // This will fail because productId is null
        kafkaTemplate.send("product-views", new ProductViewedEvent(null, "user-123", System.currentTimeMillis()));
    }
}
