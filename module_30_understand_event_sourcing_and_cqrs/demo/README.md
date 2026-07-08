# Module 30 - CQRS with Spring Events

## Demo Walkthrough

In this demo, we implement the CQRS pattern using Spring Application Events. We separate the write-model from the read-model by publishing and consuming internal asynchronous events.

### `ProductReadService.java` — Core Implementation

```java
@EventListener
    public void onProductCreated(ProductCreatedEvent event) {
        READ_MODEL.put(event.id(), new ProductView(event.id(), event.name(), event.price()));
    }
```

### Execution Workflow

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `ApplicationEventPublisher` | Publish an event using `ApplicationEventPublisher` when a write occurs. |
| 2 | `@EventListener` | Create a separate read service with an `@EventListener` method. |
| 3 | Step 3 | Update a read-optimized data structure when the event is received. |


### Expected Output

```
Application started successfully.
Expected API behaviors active.
```

### Key Concepts Demonstrated
- **Command Query Responsibility Segregation (CQRS)**
- **Spring `@EventListener` and `ApplicationEventPublisher`**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
