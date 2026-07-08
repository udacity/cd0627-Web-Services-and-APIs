package com.ecommerce.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InternalInventoryController {
    @GetMapping("/internal/inventory/{id}")
    public String getStatus(@PathVariable long id) {
        return "{\"status\": \"IN_STOCK\", \"count\": 42}";
    }
}
