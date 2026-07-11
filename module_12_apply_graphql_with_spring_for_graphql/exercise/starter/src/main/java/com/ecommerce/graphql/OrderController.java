package com.ecommerce.graphql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderController(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @QueryMapping
    public List<Order> orders() {
        return orderRepository.findAll();
    }

    // TODO (Step 1) 1: Implement a naïve @SchemaMapping for "customer" on "Order".
    // It should use customerRepository.findById(order.customerId()).
    // Add a log statement to observe the N+1 problem in the console when querying.
    // 
    // TODO (Step 2) 2: Once you see the N+1 problem, comment out the @SchemaMapping and replace it with @BatchMapping.
    // The @BatchMapping should take a List<Order> and return a Map<Order, Customer>.
    // It should use customerRepository.findAllByIds() to fetch all customers in ONE call.

    // TODO (Step 3): Add a @MutationMapping for "createOrder".
    // It should accept @Argument Double totalAmount and @Argument Long customerId.
    // Use orderRepository.addOrder() to save and return the new Order.
}
