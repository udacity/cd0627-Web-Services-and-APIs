package com.ecommerce.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InternalCustomerController {
    @GetMapping("/internal/customers/{id}")
    public String getCustomerName(@PathVariable long id) {
        return "John Doe";
    }
}
