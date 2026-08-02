# Module 9 — Error Handling and Problem Details — Exercise Instructions

## Exercise Overview

Clients are complaining about messy stack traces in the API responses. You need to implement a global error handler using `@RestControllerAdvice` that returns standardized RFC 7807 `ProblemDetail` JSON for all exceptions.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

1. In `src/main/java/com/ecommerce/order/GlobalRestExceptionHandler.java`, add a **generic `Exception.class` handler** (Step 1) returning a `ProblemDetail` with status **500** and a static support message.

2. Add an **`OrderNotFoundException` handler** (Step 2) returning a `ProblemDetail` with status **404 Not Found**.

3. Add an **`InvalidOrderStateException` handler** (Step 3) returning a `ProblemDetail` with status **422 Unprocessable Entity**.

4. Add a **`MethodArgumentNotValidException` handler** (Step 4) returning a `ProblemDetail` with status **400 Bad Request**. Iterate through the `BindingResult` to format a custom error string with field-level details.

> [!NOTE]
> The `@RestControllerAdvice` annotation is already on the class. You only need to add the `@ExceptionHandler` methods.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

```bash
mvn spring-boot:run
```

Test with:
```bash
# Trigger 404
curl http://localhost:8080/orders/999

# Trigger 422 (cancel an already-cancelled order)
curl -X POST http://localhost:8080/orders/1/cancel
curl -X POST http://localhost:8080/orders/1/cancel
```

---

## Success Criteria

- [ ] `OrderNotFoundException` returns a clean **404** JSON error (no stack trace).
- [ ] `InvalidOrderStateException` returns **422 Unprocessable Entity**.
- [ ] Validation errors return **400** with field-level details.
- [ ] Generic exceptions return **500** with a support message.
- [ ] All responses conform to the RFC 7807 `ProblemDetail` specification.
