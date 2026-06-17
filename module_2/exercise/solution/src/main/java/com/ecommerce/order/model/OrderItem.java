package com.ecommerce.order.model;

import java.math.BigDecimal;

/**
 * A single line item within an Order.
 *
 * <p>Fields:
 * <ul>
 *   <li>{@code itemId}    – Unique identifier of this line item.</li>
 *   <li>{@code productId} – Reference to the product being ordered.</li>
 *   <li>{@code name}      – Product name at the time of purchase.</li>
 *   <li>{@code quantity}  – Number of units ordered.</li>
 *   <li>{@code unitPrice} – Price per unit at the time of purchase.</li>
 * </ul>
 */
public record OrderItem(
        long itemId,
        long productId,
        String name,
        int quantity,
        BigDecimal unitPrice
) {}
