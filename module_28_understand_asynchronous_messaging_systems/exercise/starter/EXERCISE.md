# Module 28 - Asynchronous Messaging Systems - Exercise Instructions

## Exercise Overview

Your event-driven architecture is failing because poisoned messages are causing consumers to crash repeatedly. You must implement retries and a Dead Letter Queue (DLQ).

---

## Prerequisites
- **Java 21+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

> [!NOTE]
> **Deep Dive:** Spring Kafka's `@RetryableTopic` handles backoff and DLQ routing automatically without blocking the consumer thread. When an exception occurs, it intercepts the error and publishes the message to a dedicated backoff topic (e.g., `inventory-topic-retry-1000`). If all retries fail, it routes the message to the Dead Letter Topic (DLT).

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | Add `@RetryableTopic` here with 3 attempts. | `src/main/java/com/ecommerce/kafka/InventoryConsumer.java` |
| 2 | Configure exponential `@Backoff` (e.g. wait 1s, then 2s, then 4s). | `src/main/java/com/ecommerce/kafka/InventoryConsumer.java` |
| 3 | Exclude MalformedOrderException.class from retries entirely. | `src/main/java/com/ecommerce/kafka/InventoryConsumer.java` |
| 4 | Create beans for EmbeddedKafkaBroker and NewTopic | `src/main/java/com/ecommerce/kafka/KafkaLocalConfig.java` |


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
