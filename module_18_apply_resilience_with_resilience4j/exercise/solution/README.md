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

| Step | Task |
|------|-----------|
| 1 | In `src/main/java/com/ecommerce/resilience/OrderController.java`, add the `@CircuitBreaker` annotation to the fragile payment call. |
| 2 | Define a `fallbackMethod` that returns a cached or default response when the circuit is open. |


### Key Concepts Demonstrated
- **Resilience4j Circuit Breaker**
- **Fallback Methods**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
