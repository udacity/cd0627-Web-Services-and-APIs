package com.ecommerce.cqrs;

public record PlaceOrderCommand(String orderId, String item, int quantity) {}
