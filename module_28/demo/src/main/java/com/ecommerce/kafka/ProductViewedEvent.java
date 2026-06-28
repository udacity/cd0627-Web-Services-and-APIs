package com.ecommerce.kafka;

public record ProductViewedEvent(String productId, String userId, long timestamp) {
}
