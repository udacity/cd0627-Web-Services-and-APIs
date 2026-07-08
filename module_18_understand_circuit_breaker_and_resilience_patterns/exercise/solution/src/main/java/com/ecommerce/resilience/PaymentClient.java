package com.ecommerce.resilience;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentClient {

    private static final Logger log = LoggerFactory.getLogger(PaymentClient.class);

    public Map<String, Object> processPayment(String type) {
        log.info("Attempting to process payment of type: {}", type);

        if ("TIMEOUT".equals(type)) {
            throw new RuntimeException("Payment Gateway Timeout");
        }
        
        if ("INVALID_CARD".equals(type)) {
            throw new InvalidCreditCardException("Card expired or invalid");
        }

        return Map.of("status", "SUCCESS", "transactionId", "TXN-12345");
    }
}
