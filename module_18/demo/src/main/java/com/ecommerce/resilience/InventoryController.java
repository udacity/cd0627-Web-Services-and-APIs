package com.ecommerce.resilience;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InventoryController {

    private final InventoryClient inventoryClient;

    public InventoryController(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    @GetMapping("/api/inventory")
    public Map<String, Object> getInventory(@RequestParam(defaultValue = "false") boolean fail) {
        return inventoryClient.checkInventory(fail);
    }
}
