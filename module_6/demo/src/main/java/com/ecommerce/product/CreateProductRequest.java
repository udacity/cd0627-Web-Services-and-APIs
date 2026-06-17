package com.ecommerce.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record CreateProductRequest(
    @NotBlank String name,
    @Positive BigDecimal price
) {}
