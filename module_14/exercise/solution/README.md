# Module 14 - Spring Caching - Solution

## Solution Walkthrough

The solution drastically improves read performance. The framework intercepts calls, checks the in-memory cache manager, and returns the cached value if present.

### `OrderController.java` — The Implementation

```java
@PostMapping("/orders")
    public ResponseEntity<String> placeOrder(@RequestParam String productId) {
        // Network Bridge: Synchronous REST call to check inventory
        InventoryClient.InventoryResponse response = inventoryClient.checkInventory(productId);

        if (!response.inStock()) {
            return ResponseEntity.badRequest().body("Item is out of stock.");
        }

        return ResponseEntity.ok("Order placed successfully for product: " + productId);
    }
```

### Step-by-step Design Decisions:

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `@EnableCaching` | Add `@EnableCaching` to the main application class. |
| 2 | `@Cacheable("products")` | Annotate the read method with `@Cacheable("products")`. |
| 3 | `@CacheEvict` | Annotate the update/delete methods with `@CacheEvict` to prevent stale data. |


### Expected Output

```
══════════════════════════════════════════
 Integration Successful 
══════════════════════════════════════════
```

### Key Concepts Demonstrated
- **`@Cacheable` for read-through caching**
- **`@CacheEvict` for cache invalidation**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
