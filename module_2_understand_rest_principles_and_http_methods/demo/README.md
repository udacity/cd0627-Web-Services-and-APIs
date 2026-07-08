# Module 2 - REST Controllers and Data Binding

## Demo Walkthrough

This demo illustrates the fundamentals of Spring Boot REST controllers. We shift from manual servlet mapping to a declarative approach using annotations, relying on Spring's auto-configured Jackson `HttpMessageConverter` to seamlessly bind JSON payloads to Java records or POJOs.

### `PatchProductRequest.java` — Core Implementation

```java
public class PatchProductRequest {

    private String name;
    private String description;
    private BigDecimal price;

    // Default constructor required for Jackson deserialization
    public PatchProductRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
```

### Execution Workflow

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `OrderController.java` | Open `OrderController.java`. |
| 2 | `@RestController` | Annotate the class with `@RestController`. |
| 3 | `@GetMapping("/{id}")` | Implement `@GetMapping("/{id}")` and bind the ID using `@PathVariable`. |


### Expected Output

```
Application started successfully.
Expected API behaviors active.
```

### Key Concepts Demonstrated
- **`@RestController` for JSON APIs**
- **Data binding with `@PathVariable` and `@RequestBody`**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
