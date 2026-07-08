# Module 4 - Building REST APIs with Spring Boot

## Demo Walkthrough

This demo explores building RESTful services using Spring Boot, covering controllers, routing, and HTTP interfaces for external communication.

### `ProductController.java` — Core Implementation

```java
@RestController
@RequestMapping("/products")
public class ProductController {

    private final InventoryClient inventoryClient;

    public ProductController(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    // Step 1: Baseline CRUD & ResponseEntity
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProduct(@PathVariable long id) {
        // Step 3: Use Declarative HTTP Client
        String inventory = inventoryClient.getInventoryStatus(id);
        return ResponseEntity.ok(Map.of("id", id, "name", "Demo Product", "inventory", inventory));
    }
```

### Key Concepts Demonstrated
- **Spring Boot REST APIs**
- **HTTP Clients**
- **API Versioning**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
