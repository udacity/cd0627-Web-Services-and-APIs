package com.ecommerce.graphql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        log.info("Fetching all orders");
        return orderRepository.findAll();
    }

    // Fixed N+1 Problem using @BatchMapping
    @BatchMapping(typeName = "Order", field = "customer")
    public Map<Order, Customer> customer(List<Order> orders) {
        log.info("BatchMapping triggered for {} orders", orders.size());
        
        List<Long> customerIds = orders.stream()
                .map(Order::customerId)
                .collect(Collectors.toList());

        Map<Long, Customer> customersById = customerRepository.findAllByIds(customerIds);

        return orders.stream()
                .collect(Collectors.toMap(
                        order -> order,
                        order -> customersById.get(order.customerId())
                ));
    }

    @MutationMapping
    public Order createOrder(@Argument Double totalAmount, @Argument Long customerId) {
        log.info("Creating new order for customer {}", customerId);
        Order newOrder = new Order(null, totalAmount, "PENDING", customerId);
        return orderRepository.addOrder(newOrder);
    }
}
