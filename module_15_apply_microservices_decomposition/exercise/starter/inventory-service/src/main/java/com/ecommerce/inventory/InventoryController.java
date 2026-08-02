package com.ecommerce.inventory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// TODO (Step 1): Add @RestController annotation (already provided)
// TODO (Step 2): Create InventoryResponse record with productId (String) and inStock (boolean)
// TODO (Step 3): Implement GET /inventory/{productId} endpoint
@RestController
public class InventoryController {

    public record InventoryResponse(String productId, boolean inStock) {}

    @GetMapping("/inventory/{productId}")
    public InventoryResponse checkInventory(@PathVariable String productId) {
        // TODO (Step 4): Replace this stub with real inventory lookup logic
        boolean inStock = !productId.equals("OUT_OF_STOCK_ID");
        return new InventoryResponse(productId, inStock);
    }
}
