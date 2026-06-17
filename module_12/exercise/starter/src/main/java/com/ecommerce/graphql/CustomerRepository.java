package com.ecommerce.graphql;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CustomerRepository {

    public Customer findById(Long id) {
        return new Customer(id, "Customer " + id, "customer" + id + "@example.com");
    }

    public Map<Long, Customer> findAllByIds(List<Long> ids) {
        return ids.stream()
                .distinct()
                .collect(Collectors.toMap(
                        id -> id,
                        id -> new Customer(id, "Customer " + id, "customer" + id + "@example.com")
                ));
    }
}
