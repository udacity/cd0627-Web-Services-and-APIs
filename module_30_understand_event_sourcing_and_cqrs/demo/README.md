# Module 30 - Event Sourcing and CQRS

## Demo Walkthrough

In this demo, we implement the CQRS pattern to separate the write-model from the read-model.

### `ProductReadService.java` — Core Implementation

```java
@RestController
public class ProductReadService {

    // The Ephemeral Projection
    private final ConcurrentHashMap<String, ProductView> READ_MODEL = new ConcurrentHashMap<>();

    @EventListener
    public void onProductCreated(ProductCreatedEvent event) {
        READ_MODEL.put(event.id(), new ProductView(event.id(), event.name(), event.price()));
    }

    @GetMapping("/products")
    public Collection<ProductView> getProducts() {
        return READ_MODEL.values();
    }
```

### Key Concepts Demonstrated
- **Command Query Responsibility Segregation (CQRS)**
- **Event Sourcing**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
