package com.ecommerce.graphql;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class OrderRepository {

    private final Map<Long, Order> orders = new ConcurrentHashMap<>();

    public OrderRepository() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            orders.put((long) i, new Order((long) i, 100.0 * i, "ACTIVE", (long) i));
        });
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }
}
