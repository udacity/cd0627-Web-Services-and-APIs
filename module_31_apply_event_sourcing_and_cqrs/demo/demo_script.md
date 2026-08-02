# Demo Walkthrough: Event Sourcing and CQRS (Module 31)

**Focus:** The Event Store as the Single Source of Truth
**Target Length:** 5 - 7 minutes
**Files:** `ProductWriteService.java`, `ProductReadService.java`

---

## 0:00 – 1:15 | Introduction & The Problem

*(Screen showing `ProductWriteService.java` open in the IDE)*

"Welcome back. In this demo, we are going to look at Event Sourcing and CQRS — Command Query Responsibility Segregation.

"In traditional systems, we save the current state to a database. If a product's price changes from 100 to 120, we overwrite 100 with 120. The original price is lost. We cannot answer 'What was the price last Tuesday?' because we only keep the latest snapshot.

"Event Sourcing takes a different approach: instead of saving state, we save events. 'Product created with price 100', 'Price updated to 120.' The event log is the source of truth, and the current state is derived by replaying those events."

## 1:15 – 3:00 | The Write Side: Commands → Events → Event Store

*(Highlight `ProductWriteService.java`)*

"Let's look at the write side. The `ProductWriteService` accepts commands — in this case, a `CreateProductCommand`. It converts the command into an event — `ProductCreatedEvent` — with a timestamp.

"The event is appended to the `EVENT_STORE` — a simple append-only list. We never update or delete entries. Every event that has ever happened is preserved.

"Then we call `publisher.publishEvent(event)` to broadcast the event to anyone listening. This is where the read side picks it up."

## 3:00 – 4:30 | The Read Side: Events → Projections

*(Switch tabs to `ProductReadService.java`)*

"The read side is completely separate. `ProductReadService` listens for events using `@EventListener`. When a `ProductCreatedEvent` arrives, it creates a `ProductView` and stores it in a `ConcurrentHashMap` — the read model.

"The `GET /products` endpoint queries this read model directly. It never touches the event store. This is the 'segregation' in CQRS — reads and writes use different data structures optimized for their purpose.

"The read model is ephemeral — it can be destroyed and rebuilt at any time by replaying all events from the event store. This is one of the most powerful properties of event sourcing."

## 4:30 – 5:30 | Running & Observing

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s -X POST http://localhost:8080/products -H "Content-Type: application/json" -d '{"id":"P1","name":"Laptop","price":999.99}'`)*

*(🖥️ Terminal: `curl -s http://localhost:8080/products | jq`)*

"Let's test it. We create a product, and the read model is updated via the event. `GET /products` returns the projected view.

"The event store contains the full history — every event with its timestamp — while the read model contains only the current state optimized for queries."

## 5:30 – 6:00 | Outro & Summary

"To summarize: Event Sourcing stores events, not state. The event store is the single source of truth. CQRS separates write operations (commands → events) from read operations (events → projections). The read model can be rebuilt anytime by replaying the event store.

"Thanks for watching, and I'll see you in the next module."
