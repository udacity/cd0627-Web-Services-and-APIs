# Module 30 - CQRS with Spring Events - Solution

## Solution Walkthrough

The solution implements a lightweight CQRS architecture without heavy frameworks. The Write Service processes commands and acts as the event source, while the Read Service asynchronously consumes events to build an optimized projection.

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
    // ...
}
```

### Step-by-step Design Decisions:

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `ApplicationEventPublisher` | Publish an event using `ApplicationEventPublisher` when a write occurs. |
| 2 | `@EventListener` | Create a separate read service with an `@EventListener` method. |
| 3 | Step 3 | Update a read-optimized data structure when the event is received. |


### Expected Output

```
══════════════════════════════════════════
 Integration Successful 
══════════════════════════════════════════
```

### Key Concepts Demonstrated
- **Command Query Responsibility Segregation (CQRS)**
- **Spring `@EventListener` and `ApplicationEventPublisher`**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
