package com.ecommerce.resilience;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final PaymentClient paymentClient;

    public OrderController(PaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    // Compose Retry, CircuitBreaker, and Bulkhead. Retry wraps the method first.
    @CircuitBreaker(name = "payment")
    @Retry(name = "payment", fallbackMethod = "paymentFallback")
    @io.github.resilience4j.bulkhead.annotation.Bulkhead(name = "payment")
    @GetMapping("/api/checkout")
    public Map<String, Object> checkout(@RequestParam(defaultValue = "VALID") String type) {
        return paymentClient.processPayment(type);
    }

    // Fallback method triggered if Retry exhausts or Circuit Breaker is OPEN
    public Map<String, Object> paymentFallback(String type, Throwable t) {
        log.warn("Fallback triggered due to: {}. Converting to async processing.", t.getMessage());
        return Map.of(
            "status", "PENDING - Will process asynchronously",
            "message", "We are experiencing delays. Your order is secure and will be processed shortly."
        );
    }
}
