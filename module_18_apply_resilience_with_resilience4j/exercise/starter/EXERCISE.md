# Module 18 — Circuit Breaker and Resilience Patterns — Exercise Instructions

## Exercise Overview

The payment downstream service is flaky and causing your entire order API to crash. You need to implement resilience patterns using Resilience4j — including Retry with exponential backoff, a Circuit Breaker, and a fallback method.

---

## Prerequisites
- **Java 23+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### YAML Configuration (`application.yml`)

1. Under `resilience4j.retry.instances.payment:`, configure **3 max attempts** with exponential backoff (Step 1).

2. Under `resilience4j.circuitbreaker.instances.payment:`, configure the **sliding window size**, **failure-rate-threshold**, and **wait-duration-in-open-state** (Step 2). Also configure `ignoreExceptions` to exclude `InvalidCreditCardException` from tripping the circuit.

### Controller (`OrderController.java`)

3. Implement the **fallback method** `checkoutFallback` (Step 3) — return a "PENDING — Will process asynchronously" payload when the circuit is open or retries are exhausted.

> [!NOTE]
> The `@CircuitBreaker` and `@Retry` annotations are already present on the `checkout` method. Your task is to configure their behavior in YAML and implement the fallback.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

```bash
mvn spring-boot:run
```

Test the checkout endpoint:
```bash
curl http://localhost:8080/api/checkout
```

The payment service randomly fails — call it multiple times to observe retry behavior and circuit breaker tripping.

---

## Success Criteria

- [ ] When the payment service fails, retries are attempted (visible in logs).
- [ ] After repeated failures, the circuit trips open and the fallback is triggered.
- [ ] `InvalidCreditCardException` is NOT retried (business error, not transient).
- [ ] The fallback returns a graceful "PENDING" response instead of a 500 error.
