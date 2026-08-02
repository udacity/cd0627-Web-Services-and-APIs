package com.ecommerce.resilience;

public class InvalidCreditCardException extends RuntimeException {
    public InvalidCreditCardException(String message) {
        super(message);
    }
}
