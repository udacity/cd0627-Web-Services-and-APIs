# Module 8 - Error Handling and Problem Details - Solution

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

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | 1: Add Exception.class handler returning static support message (500) | `src/main/java/com/ecommerce/order/GlobalRestExceptionHandler.java` |
| 2 | 2: Add OrderNotFoundException handler returning 404 | `src/main/java/com/ecommerce/order/GlobalRestExceptionHandler.java` |
| 3 | 3: Add InvalidOrderStateException handler returning 422 (UNPROCESSABLE_ENTITY) | `src/main/java/com/ecommerce/order/GlobalRestExceptionHandler.java` |
| 4 | 4: Add MethodArgumentNotValidException handler returning 400. Iterate through BindingResult to format custom error string. | `src/main/java/com/ecommerce/order/GlobalRestExceptionHandler.java` |


### Key Concepts Demonstrated
- **`@RestControllerAdvice`**
- **RFC 7807 `ProblemDetail` specification**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
