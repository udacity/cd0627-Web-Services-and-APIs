# Module 32 - API Documentation and Testing

## Demo Walkthrough

This final demo illustrates how to auto-generate interactive API documentation using Springdoc.

### `ProductController.java` — Core Implementation

```java
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductRecord getProduct(@PathVariable String id) {
        return productService.findById(id);
    }
```

### Key Concepts Demonstrated
- **OpenAPI Specification**
- **Springdoc for auto-generation**
- **Swagger UI**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
