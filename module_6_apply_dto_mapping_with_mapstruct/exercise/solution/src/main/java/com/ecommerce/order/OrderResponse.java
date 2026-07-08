package com.ecommerce.order;

import java.math.BigDecimal;

public record OrderResponse(
    Long id,
    BigDecimal totalAmount,
    String status
) {}
