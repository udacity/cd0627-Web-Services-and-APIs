package com.ecommerce.docs;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order createOrder(CreateOrderRequest req) {
        if (req.itemIds() == null || req.itemIds().isEmpty()) {
            throw new IllegalArgumentException("Items cannot be empty");
        }
        Order order = new Order(UUID.randomUUID().toString(), String.join(",", req.itemIds()), "CREATED");
        return repository.save(order);
    }

    public Order getOrder(String id) {
        return repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    public void cancelOrder(String id) {
        Order order = getOrder(id);
        if ("SHIPPED".equals(order.getStatus())) {
            throw new InvalidStateException("Cannot cancel a shipped order");
        }
        order.setStatus("CANCELLED");
        repository.save(order);
    }
}
