package com.ecommerce.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public Map<String, Object> getProduct(@PathVariable long id) {
        if (id == 500) {
            throw new RuntimeException("Simulated database failure");
        }
        if (id > 100) {
            throw new ProductNotFoundException("Product " + id + " not found");
        }
        return Map.of("id", id, "name", "Product " + id, "price", 29.99);
    }
}
