package com.ecommerce.order;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/{id}")
    public String getOrder(@PathVariable long id) {
        if (id > 100) {
            throw new OrderNotFoundException("Order " + id + " not found");
        }
        return "Order " + id;
    }

    @PostMapping("/{id}/cancel")
    public String cancelOrder(@PathVariable long id) {
        if (id == 50) {
            throw new InvalidOrderStateException("Cannot cancel an already shipped order");
        }
        return "Order " + id + " cancelled";
    }

    @PostMapping
    public String createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return "Order created";
    }
}
