package com.ecommerce.graphql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CustomerRepository {

    private static final Logger log = LoggerFactory.getLogger(CustomerRepository.class);

    public Customer findById(Long id) {
        log.info("Fetching customer by ID: {}", id);
        return new Customer(id, "Customer " + id, "customer" + id + "@example.com");
    }

    public Map<Long, Customer> findAllByIds(List<Long> ids) {
        log.info("Batch fetching {} customers in one call", ids.size());
        return ids.stream()
                .distinct()
                .collect(Collectors.toMap(
                        id -> id,
                        id -> new Customer(id, "Customer " + id, "customer" + id + "@example.com")
                ));
    }
}
