package com.ecommerce.cqrs;

public record CreateProductCommand(String id, String name, double price) {}
