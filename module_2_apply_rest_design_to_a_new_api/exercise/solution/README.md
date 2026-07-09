# Module 2 - REST Principles and HTTP Methods - Solution

## Solution Walkthrough

The solution defines the REST contract logically using Spring MVC controllers and annotations.

### `OrderRepository.java` — The Implementation

```java
public class OrderRepository {

    private final Map<Long, Order> store = new ConcurrentHashMap<>();

    public OrderRepository() {
        Order o1 = new Order(
                1L, "CUST-001", OrderStatus.ACTIVE,
                Instant.parse("2025-01-15T10:30:00Z"),
                List.of(
                        new OrderItem(101L, 1L, "Wireless Headphones", 1, new BigDecimal("149.99")),
                        new OrderItem(102L, 3L, "USB-C Hub", 2, new BigDecimal("39.99"))
                )
        );

        Order o2 = new Order(
                2L, "CUST-002", OrderStatus.ACTIVE,
                Instant.parse("2025-01-16T14:00:00Z"),
                List.of(
                        new OrderItem(201L, 2L, "Mechanical Keyboard", 1, new BigDecimal("89.99"))
                )
        );

        store.put(o1.getId(), o1);
        store.put(o2.getId(), o2);
    }
    // ...
}
```

### Step-by-step Design Decisions:

1. Implement the `GET /orders/{id}` method.
2. Implement the `GET /orders/{id}/items` method.
3. Implement the `POST /orders/{id}/cancel` method.


### Key Concepts Demonstrated
- **REST Nouns and Verbs**
- **Nested Resources**
- **Idempotency**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
