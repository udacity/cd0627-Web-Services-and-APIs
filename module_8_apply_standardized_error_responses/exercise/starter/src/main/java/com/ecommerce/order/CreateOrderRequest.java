package com.ecommerce.order;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record CreateOrderRequest(
    @Positive BigDecimal totalAmount,
    @FutureOrPresent Instant deliveryDate,
    @NotEmpty List<Long> itemIds
) {}
