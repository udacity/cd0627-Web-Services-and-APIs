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

1. In `src/main/java/com/ecommerce/cqrs/OrderWriteService.java`, publish an event when a write occurs.
2. In `src/main/java/com/ecommerce/cqrs/OrderReadService.java`, update a read-optimized data structure when the event is received.


### Key Concepts Demonstrated
- **Command Query Responsibility Segregation (CQRS)**
- **Event Sourcing**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
