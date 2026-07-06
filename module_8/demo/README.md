# Module 8 - Global Error Handling

## Demo Walkthrough

In this demo, we tackle global error handling. Instead of repeating `try/catch` blocks in every controller, we use Spring's `@ControllerAdvice` to globally intercept exceptions.

### `ProductController.java` — Core Implementation

```java
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public String getProduct(@PathVariable long id) {
        if (id == 500) {
            throw new RuntimeException("Simulated database failure");
        }
        if (id > 100) {
            throw new ProductNotFoundException("Product " + id + " not found");
        }
        return "Product " + id;
    }
```

### Execution Workflow

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `GlobalExceptionHandler` | Create a `GlobalExceptionHandler` class annotated with `@RestControllerAdvice`. |
| 2 | `@ExceptionHandler(OrderNotFoundException.class)` | Write a method annotated with `@ExceptionHandler(OrderNotFoundException.class)`. |
| 3 | `ProblemDetail` | Return a `ProblemDetail` object with a 404 status. |


### Expected Output

```
Application started successfully.
Expected API behaviors active.
```

### Key Concepts Demonstrated
- **`@RestControllerAdvice` for cross-cutting error handling**
- **RFC 7807 `ProblemDetail` specification**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
