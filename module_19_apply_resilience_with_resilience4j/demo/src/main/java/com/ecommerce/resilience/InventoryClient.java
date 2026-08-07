package com.ecommerce.resilience;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InventoryClient {

    private static final Logger log = LoggerFactory.getLogger(InventoryClient.class);

    // Order: Retry wraps CircuitBreaker (default).
    // Fallback is on @Retry (the outer decorator) so retries happen first,
    // then after all attempts are exhausted, the fallback triggers.
    @Retry(name = "inventory", fallbackMethod = "inventoryFallback")
    @CircuitBreaker(name = "inventory")
    public Map<String, Object> checkInventory(boolean simulateFailure) {
        log.info("Attempting to check inventory. simulateFailure={}", simulateFailure);
        
        if (simulateFailure) {
            throw new RuntimeException("Database connection reset");
        }
        
        return Map.of("status", "SUCCESS", "inStock", true);
    }

    // Fallback method — called after retries are exhausted or circuit is open
    public Map<String, Object> inventoryFallback(boolean simulateFailure, Throwable t) {
        log.warn("Fallback triggered due to: {}", t.getMessage());
        return Map.of("status", "FALLBACK", "inStock", false, "message", "Service temporarily unavailable. Please try again later.");
    }
}
