# Module 6 - Spring Security

## Demo Walkthrough

This demo focuses on securing a web application using Spring Security. We demonstrate how to configure in-memory users and enforce HTTP Basic authentication.

### `ProductMapperImpl.java` — Core Implementation

```java
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponse toResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        BigDecimal price = null;

        id = product.getId();
        name = product.getName();
        price = product.getPrice();

        ProductResponse productResponse = new ProductResponse( id, name, price );

        return productResponse;
    }
    // ...
}
```

### Execution Workflow

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `SecurityConfig` | Create a `SecurityConfig` class with `@EnableWebSecurity`. |
| 2 | `SecurityFilterChain` | Define a `SecurityFilterChain` bean to require authentication for `/api/**`. |
| 3 | `@PreAuthorize("hasRole('ADMIN')")` | Add `@PreAuthorize("hasRole('ADMIN')")` to the delete endpoint. |


### Expected Output

```
Application started successfully.
Expected API behaviors active.
```

### Key Concepts Demonstrated
- **`SecurityFilterChain` for route protection**
- **Method-level security with `@PreAuthorize`**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
