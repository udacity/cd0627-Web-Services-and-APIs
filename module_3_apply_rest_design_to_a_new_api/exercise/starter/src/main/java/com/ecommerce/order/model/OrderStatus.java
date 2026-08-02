package com.ecommerce.order.model;

/**
 * Represents the lifecycle state of an Order.
 *
 * <p>An order starts as ACTIVE, and may transition to CANCELLED.
 * Once CANCELLED it cannot be changed again.
 */
public enum OrderStatus {
    ACTIVE,
    CANCELLED
}
