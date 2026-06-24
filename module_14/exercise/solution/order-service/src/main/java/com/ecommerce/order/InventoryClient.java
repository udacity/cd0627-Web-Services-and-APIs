package com.ecommerce.order;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryClient {

    record InventoryResponse(String productId, boolean inStock) {}

    @GetExchange("/inventory/{productId}")
    InventoryResponse checkInventory(@PathVariable("productId") String productId);
}
