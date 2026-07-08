package com.ecommerce.order.repository;

import com.ecommerce.order.model.Order;
import com.ecommerce.order.model.OrderItem;
import com.ecommerce.order.model.OrderStatus;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory order store.
 *
 * <p>Pre-seeded with two sample orders so the API returns meaningful data
 * from the moment the application starts — no database setup required.
 *
 * <p>Order 1 is ACTIVE with two items.
 * Order 2 is also ACTIVE with one item.
 */
@Repository
public class OrderRepository {

    private final Map<Long, Order> store = new ConcurrentHashMap<>();

    public OrderRepository() {
        // Seed order 1
        Order o1 = new Order(
                1L, "CUST-001", OrderStatus.ACTIVE,
                Instant.parse("2025-01-15T10:30:00Z"),
                List.of(
                        new OrderItem(101L, 1L, "Wireless Headphones", 1, new BigDecimal("149.99")),
                        new OrderItem(102L, 3L, "USB-C Hub", 2, new BigDecimal("39.99"))
                )
        );

        // Seed order 2
        Order o2 = new Order(
                2L, "CUST-002", OrderStatus.ACTIVE,
                Instant.parse("2025-01-16T14:00:00Z"),
                List.of(
                        new OrderItem(201L, 2L, "Mechanical Keyboard", 1, new BigDecimal("89.99"))
                )
        );

        store.put(o1.getId(), o1);
        store.put(o2.getId(), o2);
    }

    /** Returns the order with the given id, or empty if not found. */
    public Optional<Order> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }
}
