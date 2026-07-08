# Module 28 - Asynchronous Messaging Systems - Exercise Instructions

## Exercise Overview

Your event-driven architecture is failing because poisoned messages are causing consumers to crash repeatedly. You must implement retries and a Dead Letter Queue (DLQ).

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task |
|------|-----------|
| 1 | In `src/main/java/com/ecommerce/kafka/InventoryConsumer.java`, annotate your consumer method with `@KafkaListener`. |
| 2 | Add `@RetryableTopic` to automatically route failures to a backoff topic. |


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

- [ ] Transient errors are retried successfully.
- [ ] Fatal errors are sent immediately to the DLT (Dead Letter Topic).
