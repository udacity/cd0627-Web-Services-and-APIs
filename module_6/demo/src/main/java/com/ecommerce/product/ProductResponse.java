package com.ecommerce.product;

import java.math.BigDecimal;

public record ProductResponse(
    Long id,
    String name,
    BigDecimal price
) {}
