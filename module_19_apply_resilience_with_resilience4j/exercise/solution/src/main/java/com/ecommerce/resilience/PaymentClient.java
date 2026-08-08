package com.ecommerce.resilience;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PaymentClient {

    private static final Logger log = LoggerFactory.getLogger(PaymentClient.class);

    public Map<String, Object> processPayment(String type) {
        log.info("Attempting to process payment of type: {}", type);

        if ("INVALID_CARD".equals(type)) {
            throw new InvalidCreditCardException("Card expired or invalid");
        }

        // Simulate random payment gateway timeouts (~60% failure rate)
        if ("TIMEOUT".equals(type) || ThreadLocalRandom.current().nextInt(10) < 6) {
            throw new RuntimeException("Payment Gateway Timeout");
        }

        return Map.of("status", "SUCCESS", "transactionId", "TXN-" + System.currentTimeMillis());
    }
}
