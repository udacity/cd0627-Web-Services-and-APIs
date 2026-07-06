# Module 8 - Global Error Handling - Exercise Instructions

## Exercise Overview

Clients are complaining about messy stack traces in the API responses. You need to implement a global error handler that returns standardized RFC 7807 ProblemDetail JSON for any thrown exceptions.

---

## Prerequisites
- **Java 25**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Step 1
Create a `GlobalExceptionHandler` class annotated with `@RestControllerAdvice`.

### Step 2
Write a method annotated with `@ExceptionHandler(OrderNotFoundException.class)`.

### Step 3
Return a `ProblemDetail` object with a 404 status.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] Throwing `OrderNotFoundException` returns a clean JSON error response.
- [ ] The response contains `title`, `status`, and `detail` fields.
