package com.ecommerce.product.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryClient {
    @GetExchange("/internal/inventory/{id}")
    String getInventoryStatus(@PathVariable("id") long id);
}
