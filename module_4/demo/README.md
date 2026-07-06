# Module 4 - Spring Data JPA

## Demo Walkthrough

This demo illustrates how to interact with relational databases using Spring Data JPA. We transition from writing raw SQL statements to using declarative repository interfaces.

### `ProductController.java` — Core Implementation

```java
@RequestMapping("/products")
public class ProductController {

    private final InventoryClient inventoryClient;

    public ProductController(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }
```

### Execution Workflow

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `Order.java` | Open `Order.java` and add `@Entity` and `@Id`. |
| 2 | `OrderRepository.java` | Create `OrderRepository.java` extending `CrudRepository`. |
| 3 | Step 3 | Inject the repository into your service layer to perform CRUD operations. |


### Expected Output

```
Application started successfully.
Expected API behaviors active.
```

### Key Concepts Demonstrated
- **`@Entity` for ORM mapping**
- **Spring Data `CrudRepository` for zero-boilerplate data access**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
