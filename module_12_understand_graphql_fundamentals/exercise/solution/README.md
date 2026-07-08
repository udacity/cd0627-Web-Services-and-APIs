# Module 12 - GraphQL Fundamentals - Solution

## Solution Walkthrough

The solution implements a robust GraphQL schema and maps it to Java controllers using Spring for GraphQL annotations.

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

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | 1: Implement a naïve `@SchemaMapping` for "customer" on "Order". | `src/main/java/com/ecommerce/graphql/OrderController.java` |
| 2 | 2: Once you see the N+1 problem, comment out the `@SchemaMapping` and replace it with `@BatchMapping`. | `src/main/java/com/ecommerce/graphql/OrderController.java` |


### Key Concepts Demonstrated
- **GraphQL Schemas**
- **`@QueryMapping` and `@SchemaMapping`**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
