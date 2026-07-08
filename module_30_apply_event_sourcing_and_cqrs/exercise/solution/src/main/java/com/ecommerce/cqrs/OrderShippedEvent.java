package com.ecommerce.cqrs;

public record OrderShippedEvent(String orderId) implements OrderEvent {}
