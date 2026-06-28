package com.ecommerce.cqrs;

public record OrderCancelledEvent(String orderId, String reason) implements OrderEvent {}
