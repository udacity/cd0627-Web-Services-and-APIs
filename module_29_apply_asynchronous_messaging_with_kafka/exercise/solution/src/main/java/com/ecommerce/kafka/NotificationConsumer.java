package com.ecommerce.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void consume(OrderPlacedEvent event) {
        System.out.println("NotificationConsumer received: " + event.orderId());
        System.out.println("NotificationConsumer: Sent email for " + event.orderId());
    }
}
