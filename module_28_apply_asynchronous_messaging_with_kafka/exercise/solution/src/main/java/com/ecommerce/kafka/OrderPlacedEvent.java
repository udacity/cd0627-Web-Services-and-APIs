package com.ecommerce.kafka;

public record OrderPlacedEvent(String orderId, String payload) {
}
