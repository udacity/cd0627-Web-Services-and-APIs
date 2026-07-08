# Module 6 - Spring Security - Solution

## Solution Walkthrough

The solution secures the application by providing a custom `SecurityFilterChain`. We also use `@EnableMethodSecurity` to lock down specific methods based on user roles.

### `OrderMapperImpl.java` — The Implementation

```java
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderResponse toResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        Long id = null;
        BigDecimal totalAmount = null;
        String status = null;

        id = order.getId();
        totalAmount = order.getTotalAmount();
        status = order.getStatus();

        OrderResponse orderResponse = new OrderResponse( id, totalAmount, status );

        return orderResponse;
    }
    // ...
}
```

### Step-by-step Design Decisions:

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `SecurityConfig` | Create a `SecurityConfig` class with `@EnableWebSecurity`. |
| 2 | `SecurityFilterChain` | Define a `SecurityFilterChain` bean to require authentication for `/api/**`. |
| 3 | `@PreAuthorize("hasRole('ADMIN')")` | Add `@PreAuthorize("hasRole('ADMIN')")` to the delete endpoint. |


### Expected Output

```
══════════════════════════════════════════
 Integration Successful 
══════════════════════════════════════════
```

### Key Concepts Demonstrated
- **`SecurityFilterChain` for route protection**
- **Method-level security with `@PreAuthorize`**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
