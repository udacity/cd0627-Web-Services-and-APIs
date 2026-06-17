package com.ecommerce.graphql;

public record Order(Long id, double totalAmount, String status, Long customerId) {}
