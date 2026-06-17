package com.ecommerce.order.model;

import java.time.Instant;
import java.util.List;

/**
 * Domain model for an Order.
 *
 * <p>Mutable so that {@link #cancel()} can transition the status field.
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
