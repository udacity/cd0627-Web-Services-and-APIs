package com.ecommerce.resilience;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InventoryClient {

    private static final Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @CircuitBreaker(name = "inventory", fallbackMethod = "inventoryFallback")
    public Map<String, Object> checkInventory(boolean simulateFailure) {
        log.info("Attempting to check inventory. simulateFailure={}", simulateFailure);
        
        if (simulateFailure) {
            throw new RuntimeException("Database connection reset");
        }
        
        return Map.of("inStock", true);
    }

    // Fallback method must have exact same return type and append Throwable parameter
    public Map<String, Object> inventoryFallback(boolean simulateFailure, Throwable t) {
        log.warn("Fallback triggered due to: {}", t.getMessage());
        return Map.of("inStock", false);
    }
}
