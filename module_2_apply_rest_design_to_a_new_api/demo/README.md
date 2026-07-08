# Module 2 - REST Principles and HTTP Methods

## Demo Walkthrough

This demo illustrates REST resource mapping, HTTP status codes, and the difference between PATCH and POST for state changes.

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

### Key Concepts Demonstrated
- **REST Nouns and Verbs**
- **Nested Resources**
- **Idempotency**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
