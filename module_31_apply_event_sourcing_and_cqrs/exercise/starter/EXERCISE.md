# Module 31 — Event Sourcing and CQRS — Exercise Instructions

## Exercise Overview

Your audit team needs a full history of every state change, not just the current state. You will implement the Event Sourcing pattern (append-only event store) with CQRS (separate read and write models) and a Saga for orchestrating multi-step workflows.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Read Service (`OrderReadService.java`)

1. In `handleOrderPlaced`, put a new `OrderView` into `READ_MODEL` with status **"PLACED"** (Step 1).
2. In `handleOrderCancelled`, update the `OrderView` status to **"CANCELLED"** (Step 2).
3. In `rebuildReadModel`, iterate over `OrderWriteService.EVENT_STORE` (Step 3).
4. For each event, if `OrderPlacedEvent` → put new view; if `OrderCancelledEvent` → update status (Step 4).

### Saga (`OrderSaga.java`)

5. In `onOrderPlaced`, issue a `CancelOrderCommand` to the write service — this simulates a compensating action in a saga (Step 5).

### Write Service (`OrderWriteService.java`)

6. In `placeOrder`, instantiate an `OrderPlacedEvent` (Step 6).
7. Append the event to `EVENT_STORE` (Step 7).
8. Publish the event using `ApplicationEventPublisher` (Step 8).
9. In `cancelOrder`, instantiate an `OrderCancelledEvent` (Step 9).
10. Append the event to `EVENT_STORE` (Step 10).
11. Publish the event (Step 11).

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

```bash
mvn spring-boot:run
```

Test the full CQRS cycle:
```bash
# Place an order
curl -X POST http://localhost:8080/orders

# Query the read model
curl http://localhost:8080/orders

# Cancel an order
curl -X POST http://localhost:8080/orders/ORD-001/cancel

# Verify the read model reflects the cancellation
curl http://localhost:8080/orders
```

---

## Success Criteria

- [ ] `POST /orders` creates an order and publishes an `OrderPlacedEvent`.
- [ ] `GET /orders` returns the eventually-consistent read model.
- [ ] `POST /orders/{id}/cancel` publishes an `OrderCancelledEvent`.
- [ ] The read model updates reflect events (PLACED → CANCELLED).
- [ ] The event store retains the full history of all state changes.
