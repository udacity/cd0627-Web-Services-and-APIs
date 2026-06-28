package com.ecommerce.cqrs;

public record ProductCreatedEvent(String id, String name, double price, long timestamp) {}
