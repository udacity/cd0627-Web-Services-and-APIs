package com.ecommerce.graphql;

public record Product(Long id, String name, double price, Long supplierId) {}
