package com.ecommerce.kafka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements CommandLineRunner {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void run(String... args) {
        System.out.println("Publishing order events to Kafka...");
        
        // Good event
        kafkaTemplate.send("order-events", new OrderPlacedEvent("ORD-001", "{\"item\":\"laptop\"}"));
        
        // Malformed event (missing payload)
        kafkaTemplate.send("order-events", new OrderPlacedEvent("ORD-BAD", null));
        
        // Transient error event
        kafkaTemplate.send("order-events", new OrderPlacedEvent("ORD-LOCK", "{\"item\":\"mouse\"}"));
    }
}
