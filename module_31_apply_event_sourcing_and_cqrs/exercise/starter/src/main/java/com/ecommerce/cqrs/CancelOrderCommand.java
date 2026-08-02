package com.ecommerce.cqrs;

public record CancelOrderCommand(String orderId, String reason) {}
