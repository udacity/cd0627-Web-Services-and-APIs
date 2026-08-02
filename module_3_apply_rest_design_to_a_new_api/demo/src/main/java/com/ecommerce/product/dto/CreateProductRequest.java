package com.ecommerce.product.dto;

import java.math.BigDecimal;

/**
 * Request body for creating a new product (POST /products).
 *
 * <p>We use a separate DTO instead of exposing the Product record directly
 * so that the client cannot set the {@code id} field (it is server-assigned).
 *
 * <p>Example JSON:
 * <pre>{@code
 * {
 *   "name": "Wireless Headphones",
 *   "description": "Over-ear, noise-cancelling",
 *   "price": 149.99
 * }
 * }</pre>
 */
public record CreateProductRequest(
        String name,
        String description,
        BigDecimal price
) {}
