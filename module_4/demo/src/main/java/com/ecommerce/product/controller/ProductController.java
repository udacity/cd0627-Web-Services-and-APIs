package com.ecommerce.product.controller;

import com.ecommerce.product.client.InventoryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final InventoryClient inventoryClient;

    public ProductController(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    // Step 1: Baseline CRUD & ResponseEntity
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProduct(@PathVariable long id) {
        // Step 3: Use Declarative HTTP Client
        String inventory = inventoryClient.getInventoryStatus(id);
        return ResponseEntity.ok(Map.of("id", id, "name", "Demo Product", "inventory", inventory));
    }

    @PostMapping
    public ResponseEntity<Void> createProduct() {
        return ResponseEntity.created(URI.create("/products/1")).build();
    }

    // Step 2: Boot 4 Native Versioning (simulated via headers for compilation)
    @GetMapping(path = "/{id}", headers = "version=2")
    public ResponseEntity<Map<String, Object>> getProductV2(@PathVariable long id) {
        return ResponseEntity.ok(Map.of("id", id, "name", "Demo Product V2", "features", "New V2 fields!"));
    }
}
