# Module 8 - Error Handling and Problem Details - Exercise Instructions

## Exercise Overview

Clients are complaining about messy stack traces in the API responses. You need to implement a global error handler that returns standardized RFC 7807 ProblemDetail JSON for any thrown exceptions.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

1. In `src/main/java/com/ecommerce/order/GlobalRestExceptionHandler.java`, add `@RestControllerAdvice` to the class to globally intercept exceptions.
2. Write a method annotated with `@ExceptionHandler(OrderNotFoundException.class)`.
3. Construct and return a Spring `ProblemDetail` object with a 404 status.


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
