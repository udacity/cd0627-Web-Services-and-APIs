# Module 18 - Pagination and Sorting

## Demo Walkthrough

This demo demonstrates how to handle large datasets using Pagination and Sorting. We leverage Spring Data's `Pageable` interface to optimize SQL queries automatically.

### `InventoryClient.java` — Core Implementation

```java
public class InventoryClient {

    private static final Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @CircuitBreaker(name = "inventory", fallbackMethod = "inventoryFallback")
    public Map<String, Object> checkInventory(boolean simulateFailure) {
        log.info("Attempting to check inventory. simulateFailure={}", simulateFailure);
        
        if (simulateFailure) {
            throw new RuntimeException("Database connection reset");
        }
        
        return Map.of("inStock", true);
    }

    // Fallback method must have exact same return type and append Throwable parameter
    public Map<String, Object> inventoryFallback(boolean simulateFailure, Throwable t) {
        log.warn("Fallback triggered due to: {}", t.getMessage());
        return Map.of("inStock", false);
    }
    // ...
}
```

### Execution Workflow

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `Pageable` | Update the repository method to accept a `Pageable` argument. |
| 2 | `Pageable` | Modify the controller to accept a `Pageable` parameter. |
| 3 | `Page<Order>` | Return a `Page<Order>` instead of a `List<Order>`. |


### Expected Output

```
Application started successfully.
Expected API behaviors active.
```

### Key Concepts Demonstrated
- **Spring Data `Pageable`**
- **Automatic query translation for `LIMIT` and `OFFSET`**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
