# Module 4 - Spring Data JPA - Solution

## Solution Walkthrough

The solution leverages `ListCrudRepository` to gain standard database operations without writing implementation classes. Spring Boot auto-configures the data source based on the H2 dependency.

### `InternalCustomerController.java` — The Implementation

```java
@GetMapping("/internal/customers/{id}")
    public String getCustomerName(@PathVariable long id) {
        return "John Doe";
    }
```

### Step-by-step Design Decisions:

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `Order.java` | Open `Order.java` and add `@Entity` and `@Id`. |
| 2 | `OrderRepository.java` | Create `OrderRepository.java` extending `CrudRepository`. |
| 3 | Step 3 | Inject the repository into your service layer to perform CRUD operations. |


### Expected Output

```
══════════════════════════════════════════
 Integration Successful 
══════════════════════════════════════════
```

### Key Concepts Demonstrated
- **`@Entity` for ORM mapping**
- **Spring Data `CrudRepository` for zero-boilerplate data access**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
