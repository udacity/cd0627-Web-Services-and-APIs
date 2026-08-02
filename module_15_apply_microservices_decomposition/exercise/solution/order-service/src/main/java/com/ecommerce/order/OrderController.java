package com.ecommerce.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final InventoryClient inventoryClient;

    public OrderController(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    @PostMapping("/orders")
    public ResponseEntity<String> placeOrder(@RequestParam String productId) {
        // Network Bridge: Synchronous REST call to check inventory
        InventoryClient.InventoryResponse response = inventoryClient.checkInventory(productId);

        if (!response.inStock()) {
            return ResponseEntity.badRequest().body("Item is out of stock.");
        }

        return ResponseEntity.ok("Order placed successfully for product: " + productId);
    }
}
