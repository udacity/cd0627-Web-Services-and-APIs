# Module 18 - Circuit Breaker and Resilience Patterns - Solution

## Solution Walkthrough

The solution guarantees system resilience. The `@CircuitBreaker` annotation wraps the fragile call, tripping open to protect downstream systems and returning a graceful fallback.

### `OrderController.java` — The Implementation

```java
@CircuitBreaker(name = "payment", fallbackMethod = "paymentFallback")
    @Retry(name = "payment")
    @GetMapping("/api/checkout")
    public Map<String, Object> checkout(@RequestParam(defaultValue = "VALID") String type) {
        return paymentClient.processPayment(type);
    }
```

### Step-by-step Design Decisions:

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | Add `@CircuitBreaker` with fallbackMethod | `src/main/java/com/ecommerce/resilience/OrderController.java` |
| 2 | Add `@Retry` | `src/main/java/com/ecommerce/resilience/OrderController.java` |
| 3 | Implement fallback method returning a "PENDING - Will process asynchronously" payload | `src/main/java/com/ecommerce/resilience/OrderController.java` |


### Key Concepts Demonstrated
- **Resilience4j Circuit Breaker**
- **Fallback Methods**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
