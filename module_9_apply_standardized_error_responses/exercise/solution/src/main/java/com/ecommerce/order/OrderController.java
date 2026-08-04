package com.ecommerce.order;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/{id}")
    public Map<String, Object> getOrder(@PathVariable long id) {
        if (id > 100) {
            throw new OrderNotFoundException("Order " + id + " not found");
        }
        return Map.of("id", id, "status", "ACTIVE");
    }

    @PostMapping("/{id}/cancel")
    public Map<String, Object> cancelOrder(@PathVariable long id) {
        if (id == 50) {
            throw new InvalidOrderStateException("Cannot cancel an already shipped order");
        }
        return Map.of("id", id, "status", "CANCELLED", "message", "Order " + id + " cancelled");
    }

    @PostMapping
    public Map<String, Object> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return Map.of("status", "CREATED", "message", "Order created");
    }
}

