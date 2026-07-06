# Module 12 - Jakarta Validation

## Demo Walkthrough

This demo showcases how to enforce data integrity at the edge of the application using Jakarta Validation, preventing bad data from ever reaching the service layer.

### `ProductController.java` — Core Implementation

```java
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final List<Product> products = new CopyOnWriteArrayList<>(
            IntStream.rangeClosed(1, 10)
                    .mapToObj(i -> new Product((long) i, "Product " + i, 10.0 * i, 100L + i))
                    .collect(Collectors.toList())
    );

    @QueryMapping
    public List<Product> products() {
        return products;
    }

    @MutationMapping
    public Product createProduct(@Argument String name, @Argument double price) {
        Product p = new Product((long) (products.size() + 1), name, price, 999L);
        products.add(p);
        return p;
    // ...
}
```

### Execution Workflow

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `@NotBlank` | Add `@NotBlank` and `@Positive` to the fields in your Request DTO. |
| 2 | `@Valid` | Add `@Valid` to the `@RequestBody` parameter in your controller. |
| 3 | Step 3 | Verify that invalid payloads are rejected automatically. |


### Expected Output

```
Application started successfully.
Expected API behaviors active.
```

### Key Concepts Demonstrated
- **Jakarta Validation constraints**
- **`@Valid` for edge validation**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
