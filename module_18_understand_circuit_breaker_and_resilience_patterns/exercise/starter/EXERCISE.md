# Module 18 - Circuit Breaker and Resilience Patterns - Exercise Instructions

## Exercise Overview

The payment downstream service is flaky and causing your entire order API to crash. You need to implement the Circuit Breaker pattern using Resilience4j to fail fast and provide a fallback response.

---

## Prerequisites
- **Java 21+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | Add `@CircuitBreaker` with fallbackMethod | `src/main/java/com/ecommerce/resilience/OrderController.java` |
| 2 | Add `@Retry` | `src/main/java/com/ecommerce/resilience/OrderController.java` |
| 3 | Implement fallback method returning a "PENDING - Will process asynchronously" payload | `src/main/java/com/ecommerce/resilience/OrderController.java` |


> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] When the payment service fails, the fallback is triggered.
- [ ] The circuit trips open after repeated failures, preventing further downstream calls.
