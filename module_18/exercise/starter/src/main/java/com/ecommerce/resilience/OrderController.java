package com.ecommerce.resilience;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {

    private final PaymentClient paymentClient;

    public OrderController(PaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    // TODO: Add @CircuitBreaker with fallbackMethod
    // TODO: Add @Retry
    @GetMapping("/api/checkout")
    public Map<String, Object> checkout(@RequestParam(defaultValue = "VALID") String type) {
        return paymentClient.processPayment(type);
    }

    // TODO: Implement fallback method returning a "PENDING - Will process asynchronously" payload
}
