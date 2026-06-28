package com.ecommerce.cqrs;

public record PaymentFailedEvent(String orderId, String reason) {}
