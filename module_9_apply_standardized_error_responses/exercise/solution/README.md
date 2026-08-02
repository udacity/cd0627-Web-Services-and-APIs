# Module 9 - Error Handling and Problem Details - Solution

## Solution Walkthrough

The solution centralizes exception management. When an exception occurs, the advice builds a structured `ProblemDetail` object.

### `OrderController.java` — The Implementation

```java
@RestController
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

1. In `src/main/java/com/ecommerce/order/GlobalRestExceptionHandler.java`, add `@RestControllerAdvice` to the class to globally intercept exceptions.
2. Write a method annotated with `@ExceptionHandler(OrderNotFoundException.class)`.
3. Construct and return a Spring `ProblemDetail` object with a 404 status.


### Key Concepts Demonstrated
- **`@RestControllerAdvice`**
- **RFC 7807 `ProblemDetail` specification**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
