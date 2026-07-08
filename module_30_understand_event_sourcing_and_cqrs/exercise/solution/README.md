# Module 30 - Event Sourcing and CQRS - Solution

## Solution Walkthrough

The solution implements a lightweight CQRS architecture where the Write Service acts as the event source and the Read Service builds an optimized projection.

### `OrderWriteService.java` — The Implementation

```java
public class OrderWriteService {

    public static final List<OrderEvent> EVENT_STORE = new CopyOnWriteArrayList<>();
    
    private final ApplicationEventPublisher publisher;

    public OrderWriteService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void handle(PlaceOrderCommand cmd) {
        OrderPlacedEvent event = new OrderPlacedEvent(cmd.orderId(), cmd.item(), cmd.quantity());
        EVENT_STORE.add(event);
        publisher.publishEvent(event);
    }

    public void handle(CancelOrderCommand cmd) {
        OrderCancelledEvent event = new OrderCancelledEvent(cmd.orderId(), cmd.reason());
        EVENT_STORE.add(event);
        publisher.publishEvent(event);
    }
}
```

### Step-by-step Design Decisions:

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | Put a new OrderView into READ_MODEL with status "PLACED" | `src/main/java/com/ecommerce/cqrs/OrderReadService.java` |
| 2 | Update the OrderView status to "CANCELLED" | `src/main/java/com/ecommerce/cqrs/OrderReadService.java` |
| 3 | Iterate over OrderWriteService.EVENT_STORE | `src/main/java/com/ecommerce/cqrs/OrderReadService.java` |
| 4 | For each event, if OrderPlacedEvent put new view, if OrderCancelledEvent update status. | `src/main/java/com/ecommerce/cqrs/OrderReadService.java` |
| 5 | Issue a CancelOrderCommand to the write service | `src/main/java/com/ecommerce/cqrs/OrderSaga.java` |
| 6 | Instantiate an OrderPlacedEvent | `src/main/java/com/ecommerce/cqrs/OrderWriteService.java` |
| 7 | Append to EVENT_STORE | `src/main/java/com/ecommerce/cqrs/OrderWriteService.java` |
| 8 | Publish event | `src/main/java/com/ecommerce/cqrs/OrderWriteService.java` |
| 9 | Instantiate an OrderCancelledEvent | `src/main/java/com/ecommerce/cqrs/OrderWriteService.java` |
| 10 | Append to EVENT_STORE | `src/main/java/com/ecommerce/cqrs/OrderWriteService.java` |
| 11 | Publish event | `src/main/java/com/ecommerce/cqrs/OrderWriteService.java` |


### Key Concepts Demonstrated
- **Command Query Responsibility Segregation (CQRS)**
- **Event Sourcing**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
