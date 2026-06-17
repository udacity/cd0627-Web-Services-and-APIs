package com.ecommerce.graphql;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class OrderRepository {

    private final List<Order> orders = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> new Order((long) i, 100.0 * i, "ACTIVE", (long) i))
            .collect(Collectors.toList());

    public List<Order> findAll() {
        return orders;
    }
}
