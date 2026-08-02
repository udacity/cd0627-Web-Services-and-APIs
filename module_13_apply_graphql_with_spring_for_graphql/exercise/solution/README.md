# Module 13 - GraphQL Fundamentals - Solution

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

1. In `src/main/resources/graphql/schema.graphqls`, define your Types, Queries, and Mutations.
2. In `src/main/java/com/ecommerce/graphql/OrderController.java`, annotate query methods with `@QueryMapping` and mutation methods with `@MutationMapping`.
3. Annotate nested field resolvers with `@SchemaMapping`.


### Key Concepts Demonstrated
- **GraphQL Schemas**
- **`@QueryMapping`, `@MutationMapping`, and `@SchemaMapping`**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
