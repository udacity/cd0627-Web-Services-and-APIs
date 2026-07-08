# Module 30 - Event Sourcing and CQRS - Exercise Instructions

## Exercise Overview

Your monolithic service is doing too much. You need to decouple the write operations (Command) from the read operations (Query) using CQRS.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task |
|------|-----------|
| 1 | In `src/main/java/com/ecommerce/cqrs/OrderWriteService.java`, publish an event when a write occurs. |
| 2 | In `src/main/java/com/ecommerce/cqrs/OrderReadService.java`, update a read-optimized data structure when the event is received. |


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
