# Module 30 - Event Sourcing and CQRS - Exercise Instructions

## Exercise Overview

Your monolithic service is doing too much. You need to decouple the write operations (Command) from the read operations (Query) using CQRS.

---

## Prerequisites
- **Java 21+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

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


> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] Writing an order asynchronously triggers the listener.
- [ ] The read service maintains an eventually-consistent view of the data.
