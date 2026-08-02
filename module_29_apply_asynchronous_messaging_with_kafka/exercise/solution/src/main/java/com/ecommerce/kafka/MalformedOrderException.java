package com.ecommerce.kafka;

public class MalformedOrderException extends RuntimeException {
    public MalformedOrderException(String message) {
        super(message);
    }
}
