# Module 8 - Global Error Handling - Solution

## Solution Walkthrough

The solution centralizes exception management. Whenever a specific exception is thrown, the advice intercepts it and builds a structured `ProblemDetail` object.

### `OrderController.java` — The Implementation

```java
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/{id}")
    public String getOrder(@PathVariable long id) {
        if (id > 100) {
            throw new OrderNotFoundException("Order " + id + " not found");
        }
        return "Order " + id;
    }
```

### Step-by-step Design Decisions:

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `GlobalExceptionHandler` | Create a `GlobalExceptionHandler` class annotated with `@RestControllerAdvice`. |
| 2 | `@ExceptionHandler(OrderNotFoundException.class)` | Write a method annotated with `@ExceptionHandler(OrderNotFoundException.class)`. |
| 3 | `ProblemDetail` | Return a `ProblemDetail` object with a 404 status. |


### Expected Output

```
══════════════════════════════════════════
 Integration Successful 
══════════════════════════════════════════
```

### Key Concepts Demonstrated
- **`@RestControllerAdvice` for cross-cutting error handling**
- **RFC 7807 `ProblemDetail` specification**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
