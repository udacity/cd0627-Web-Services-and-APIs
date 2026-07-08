# Module 18 - Circuit Breaker and Resilience Patterns - Exercise Instructions

## Exercise Overview

The payment downstream service is flaky and causing your entire order API to crash. You need to implement the Circuit Breaker pattern using Resilience4j to fail fast and provide a fallback response.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task |
|------|-----------|
| 1 | In `src/main/java/com/ecommerce/resilience/OrderController.java`, add the `@CircuitBreaker` annotation to the fragile payment call. |
| 2 | Define a `fallbackMethod` that returns a cached or default response when the circuit is open. |


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
