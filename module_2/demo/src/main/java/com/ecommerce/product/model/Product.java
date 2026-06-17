package com.ecommerce.product.model;

import java.math.BigDecimal;

/**
 * Domain model for a Product.
 *
 * <p>In a real application this would be a JPA {@code @Entity}.
 * For this demo we keep it as a plain Java record so we can focus
 * entirely on the REST contract rather than persistence details.
 *
 * <p>Fields:
 * <ul>
 *   <li>{@code id}          – Auto-assigned unique identifier (long).</li>
 *   <li>{@code name}        – Human-readable product name.</li>
 *   <li>{@code description} – Short marketing description.</li>
 *   <li>{@code price}       – Current price (BigDecimal for monetary precision).</li>
 * </ul>
 */
public record Product(
        long id,
        String name,
        String description,
        BigDecimal price
) {}
