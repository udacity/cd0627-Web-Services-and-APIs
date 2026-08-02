package com.ecommerce.order.controller;

import com.ecommerce.order.client.CustomerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CustomerClient customerClient;

    public OrderController(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getOrder(@PathVariable long id) {
        String customerName = customerClient.getCustomerName(id);
        return ResponseEntity.ok(Map.of("id", id, "status", "ACTIVE", "customerName", customerName));
    }
    
    @PostMapping
    public ResponseEntity<Void> createOrder() {
        return ResponseEntity.created(URI.create("/orders/1")).build();
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable long id) {
        if (id > 100) {
            return ResponseEntity.notFound().build(); // order not found
        }
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @GetMapping(path = "/{id}", headers = "version=2")
    public ResponseEntity<Map<String, Object>> getOrderV2(@PathVariable long id) {
        String customerName = customerClient.getCustomerName(id);
        return ResponseEntity.ok(Map.of("id", id, "status", "ACTIVE", "customerName", customerName, "orderSummary", "Summary for order " + id));
    }
}
