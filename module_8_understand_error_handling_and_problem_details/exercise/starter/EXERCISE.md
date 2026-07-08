# Module 8 - Error Handling and Problem Details - Exercise Instructions

## Exercise Overview

Clients are complaining about messy stack traces in the API responses. You need to implement a global error handler that returns standardized RFC 7807 ProblemDetail JSON for any thrown exceptions.

---

## Prerequisites
- **Java 21+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | 1: Add Exception.class handler returning static support message (500) | `src/main/java/com/ecommerce/order/GlobalRestExceptionHandler.java` |
| 2 | 2: Add OrderNotFoundException handler returning 404 | `src/main/java/com/ecommerce/order/GlobalRestExceptionHandler.java` |
| 3 | 3: Add InvalidOrderStateException handler returning 422 (UNPROCESSABLE_ENTITY) | `src/main/java/com/ecommerce/order/GlobalRestExceptionHandler.java` |
| 4 | 4: Add MethodArgumentNotValidException handler returning 400. Iterate through BindingResult to format custom error string. | `src/main/java/com/ecommerce/order/GlobalRestExceptionHandler.java` |


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

- [ ] Throwing `OrderNotFoundException` returns a clean JSON error response.
- [ ] The response adheres to the RFC 7807 specification.
