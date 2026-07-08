# Module 32 - Auto-Generated API Docs

## Demo Walkthrough

This final demo illustrates how to auto-generate interactive API documentation. We utilize `springdoc-openapi` to automatically inspect the application's controllers and produce a live Swagger UI dashboard.

### `ProductController.java` — Core Implementation

```java
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
```

### Execution Workflow

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `springdoc-openapi-starter-webmvc-ui` | Add the `springdoc-openapi-starter-webmvc-ui` dependency. |
| 2 | `@Operation` | Annotate your controller endpoints with `@Operation` and `@ApiResponses`. |
| 3 | `/swagger-ui.html` | Navigate to `/swagger-ui.html` in your browser to view the docs. |


### Expected Output

```
Application started successfully.
Expected API behaviors active.
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
