# Module 12 - Jakarta Validation - Solution

## Solution Walkthrough

The solution guarantees valid incoming data by annotating DTOs with rules like `@NotNull`. The `@Valid` annotation ensures Spring intercepts and rejects invalid requests before method execution.

### `CustomerRepository.java` — The Implementation

```java
public class CustomerRepository {

    private static final Logger log = LoggerFactory.getLogger(CustomerRepository.class);

    public Customer findById(Long id) {
        log.info("Fetching customer by ID: {}", id);
        return new Customer(id, "Customer " + id, "customer" + id + "@example.com");
    }

    public Map<Long, Customer> findAllByIds(List<Long> ids) {
        log.info("Batch fetching {} customers in one call", ids.size());
        return ids.stream()
                .distinct()
                .collect(Collectors.toMap(
                        id -> id,
                        id -> new Customer(id, "Customer " + id, "customer" + id + "@example.com")
                ));
    }
}
```

### Step-by-step Design Decisions:

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `@NotBlank` | Add `@NotBlank` and `@Positive` to the fields in your Request DTO. |
| 2 | `@Valid` | Add `@Valid` to the `@RequestBody` parameter in your controller. |
| 3 | Step 3 | Verify that invalid payloads are rejected automatically. |


### Expected Output

```
══════════════════════════════════════════
 Integration Successful 
══════════════════════════════════════════
```

### Key Concepts Demonstrated
- **Jakarta Validation constraints**
- **`@Valid` for edge validation**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
