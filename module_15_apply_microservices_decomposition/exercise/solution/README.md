# Module 15 - Microservices Architecture Principles - Solution

## Solution Walkthrough

The solution successfully decouples the domains into separate Spring Boot applications that communicate via well-defined REST contracts.

### `OrderController.java` — The Implementation

```java
@RestController
public class OrderController {

    private final InventoryClient inventoryClient;

    public OrderController(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

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

1. In the `order-service` project, implement an HTTP client to verify inventory via the `inventory-service`.
2. Ensure the services can run on separate ports simultaneously.


### Key Concepts Demonstrated
- **Microservices Decomposition**
- **Inter-service communication**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
