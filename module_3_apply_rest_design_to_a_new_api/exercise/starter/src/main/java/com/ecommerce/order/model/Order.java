package com.ecommerce.order.model;

import java.time.Instant;
import java.util.List;

/**
 * Domain model for an Order.
 *
 * <p>Fields:
 * <ul>
 *   <li>{@code id}         – Server-assigned unique identifier.</li>
 *   <li>{@code customerId} – Reference to the customer who placed the order.</li>
 *   <li>{@code status}     – Current lifecycle state: ACTIVE or CANCELLED.</li>
 *   <li>{@code placedAt}   – Timestamp when the order was placed.</li>
 *   <li>{@code items}      – List of line items in this order.</li>
 * </ul>
 *
 * <p>Note: This is a mutable class (not a record) because {@code status}
 * needs to change when an order is cancelled.
 */
public class Order {

    private final long id;
    private final String customerId;
    private OrderStatus status;
    private final Instant placedAt;
    private final List<OrderItem> items;

    public Order(long id, String customerId, OrderStatus status,
                 Instant placedAt, List<OrderItem> items) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.placedAt = placedAt;
        this.items = items;
    }

    public long getId()             { return id; }
    public String getCustomerId()   { return customerId; }
    public OrderStatus getStatus()  { return status; }
    public Instant getPlacedAt()    { return placedAt; }
    public List<OrderItem> getItems() { return items; }

    /** Transitions this order to CANCELLED state. */
    public void cancel() {
        this.status = OrderStatus.CANCELLED;
    }
}
