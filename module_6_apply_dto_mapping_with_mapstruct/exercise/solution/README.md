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

| Step | Task |
|------|-----------|
| 1 | In `src/main/java/com/ecommerce/order/OrderController.java`, refactor the endpoints to accept and return DTOs instead of raw Entities. |
| 2 | In `src/main/java/com/ecommerce/order/OrderMapper.java`, define the MapStruct mapping rules. |


### Key Concepts Demonstrated
- **Data Transfer Objects (DTOs)**
- **MapStruct for object mapping**
- **Layer isolation**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
