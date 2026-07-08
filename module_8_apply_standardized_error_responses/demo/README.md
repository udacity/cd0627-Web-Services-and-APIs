# Module 8 - Error Handling and Problem Details

## Demo Walkthrough

In this demo, we tackle global error handling using `@ControllerAdvice` to intercept exceptions and return standardized problem details.

### `ProductController.java` — Core Implementation

```java
@RestController
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

### Key Concepts Demonstrated
- **`@RestControllerAdvice`**
- **RFC 7807 `ProblemDetail` specification**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
