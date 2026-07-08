package com.ecommerce.cqrs;

public record OrderPlacedEvent(String orderId, String item, int quantity) implements OrderEvent {}
