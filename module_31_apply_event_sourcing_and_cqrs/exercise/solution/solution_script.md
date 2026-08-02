# Solution Walkthrough: Event Sourcing and CQRS (Module 31)

**Focus:** Order Event Store, Read Projections, and the Compensating Saga
**Target Length:** 5 - 7 minutes
**Files:** `OrderWriteService.java`, `OrderReadService.java`, `OrderSaga.java`, `OrderCommandController.java`

---

## 0:00 – 1:00 | Introduction & The Exercise

*(Screen showing the project open in the IDE)*

"Welcome back. In this video, we're going to walk through the solution to the Event Sourcing and CQRS exercise.

"Our goal was to implement a full CQRS pipeline for orders: a write service that stores events, a read service with projections, and a saga that compensates for failed payments by automatically cancelling orders."

## 1:00 – 2:30 | The Write Service

*(Switch tabs to `OrderWriteService.java`)*

"The `OrderWriteService` handles two commands: `PlaceOrderCommand` and `CancelOrderCommand`. Each command is converted into an event — `OrderPlacedEvent` or `OrderCancelledEvent`.

"The event is appended to the `EVENT_STORE` — a `CopyOnWriteArrayList` that serves as our append-only event log. Then `publisher.publishEvent()` broadcasts the event so the read side can update.

"Notice the events implement an `OrderEvent` interface with `orderId()`. This common interface allows the read service to process all event types polymorphically."

## 2:30 – 3:30 | The Read Service and Projections

*(Switch tabs to `OrderReadService.java`)*

"The `OrderReadService` maintains a read model — a `ConcurrentHashMap` of `OrderView` objects. It has two event listeners.

"`onOrderPlaced` creates a new `OrderView` with status 'PLACED'. `onOrderCancelled` finds the existing view and updates its status to 'CANCELLED'.

"The critical method is `rebuildReadModel()`. It clears the entire read model and replays every event from the event store in order. This lets us rebuild the read model from scratch — useful for schema changes, bug fixes, or new projection types."

## 3:30 – 4:30 | The Saga: Compensating Actions

*(Switch tabs to `OrderSaga.java`)*

"The `OrderSaga` is a lightweight process manager. It listens for `PaymentFailedEvent`. When a payment fails, the saga automatically issues a `CancelOrderCommand`, which flows through the write service, creates a `OrderCancelledEvent`, and updates the read model.

"This is a compensating action — instead of complex distributed transactions, we let each step succeed independently and compensate when something goes wrong downstream."

## 4:30 – 6:00 | Running & Verification

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s -X POST http://localhost:8080/orders -H "Content-Type: application/json" -d '{"orderId":"ORD-1","item":"laptop","quantity":1}'`)*

"Let's test it. We place an order. The event store records `OrderPlacedEvent`, and the read model shows status 'PLACED'.

*(🖥️ Terminal: `curl -s http://localhost:8080/orders | jq`)*

"The read model shows our order.

*(🖥️ Terminal: `curl -s -X POST http://localhost:8080/orders/ORD-1/cancel -H "Content-Type: application/json" -d '{"orderId":"ORD-1","reason":"customer request"}'`)*

"Now we cancel the order. The event store now has two events: `OrderPlacedEvent` and `OrderCancelledEvent`. The read model shows status 'CANCELLED'.

*(🖥️ Terminal: `curl -s http://localhost:8080/orders | jq`)*

"The full history is preserved in the event store. We can always answer: 'When was this order placed? When was it cancelled? Why?' Traditional systems lose this audit trail."

## 6:00 – 6:30 | Outro

"To summarize: We implemented a complete CQRS pipeline — commands create events, events update projections, and sagas handle compensating actions. The event store preserves the full history, and the read model can be rebuilt at any time.

"Great job if you got this working. I'll see you in the next module."
