# Module 6 - Request/Response Handling and DTOs - Solution

## Solution Walkthrough

The solution utilizes MapStruct to automatically generate robust mapping code, ensuring that internal database entities never leak out to the client.

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

    @Override
    public Order toEntity(CreateOrderRequest request) {
        if ( request == null ) {
            return null;
    // ...
}
```

### Step-by-step Design Decisions:

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | 1. Create OrderResponse Record (id, totalAmount, status) | `src/main/java/com/ecommerce/order/OrderController.java` |
| 2 | 2. Create CreateOrderRequest Record (totalAmount, status, deliveryDate, itemIds) | `src/main/java/com/ecommerce/order/OrderController.java` |
| 3 | 3. Add Validation to CreateOrderRequest: | `src/main/java/com/ecommerce/order/OrderController.java` |
| 4 | 4. Create OrderMapper interface using MapStruct. | `src/main/java/com/ecommerce/order/OrderController.java` |
| 5 | 5. Refactor the endpoints below to use the Records, `@Valid`, and the Mapper. | `src/main/java/com/ecommerce/order/OrderController.java` |


### Key Concepts Demonstrated
- **Data Transfer Objects (DTOs)**
- **MapStruct for object mapping**
- **Layer isolation**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
