package com.ecommerce.inventory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    public record InventoryResponse(String productId, boolean inStock) {}

    @GetMapping("/inventory/{productId}")
    public InventoryResponse checkInventory(@PathVariable String productId) {
        // Hardcoded stub logic
        boolean inStock = !productId.equals("OUT_OF_STOCK_ID");
        return new InventoryResponse(productId, inStock);
    }
}
