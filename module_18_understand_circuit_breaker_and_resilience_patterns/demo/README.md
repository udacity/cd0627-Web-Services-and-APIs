# Module 18 - Circuit Breaker and Resilience Patterns

## Demo Walkthrough

This demo illustrates how to protect your application from cascading failures using the Circuit Breaker pattern.

### `InventoryClient.java` — Core Implementation

```java
@CircuitBreaker(name = "inventory", fallbackMethod = "inventoryFallback")
    public Map<String, Object> checkInventory(boolean simulateFailure) {
        log.info("Attempting to check inventory. simulateFailure={}", simulateFailure);
        
        if (simulateFailure) {
            throw new RuntimeException("Database connection reset");
        }
        
        return Map.of("inStock", true);
    }
```

### Key Concepts Demonstrated
- **Resilience4j Circuit Breaker**
- **Fallback Methods**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
