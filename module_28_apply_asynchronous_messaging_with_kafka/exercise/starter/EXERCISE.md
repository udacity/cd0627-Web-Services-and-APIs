# Module 28 — Asynchronous Messaging with Kafka — Exercise Instructions

## Exercise Overview

Your order pipeline is too tightly coupled — the order service waits synchronously for inventory checks. You need to implement asynchronous messaging using Apache Kafka with retry and dead-letter topic handling.

---

## Prerequisites
- **Java 23+**
- **Maven 3.9+**

> [!NOTE]
> This exercise uses an **embedded Kafka broker** for local development — no external Kafka installation is required.

---

## Step-by-Step Implementation Guide

### Consumer (`InventoryConsumer.java`)

1. Add `@RetryableTopic` to the consumer method with **3 max attempts** (Step 1).
2. Configure exponential `@Backoff` — e.g., wait 1s, then 2s, then 4s (Step 2).
3. Exclude `MalformedOrderException.class` from retries entirely — these are business errors that should not be retried (Step 3).

### Kafka Configuration (`KafkaLocalConfig.java`)

4. Create Spring beans for `EmbeddedKafkaBroker` and `NewTopic` to bootstrap the embedded broker and topic on startup (Step 4).

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

```bash
mvn spring-boot:run
```

The application will start with an embedded Kafka broker. Watch the logs to observe:
- Messages being produced and consumed
- Retry attempts with exponential backoff
- Dead-letter topic routing for non-retryable exceptions

---

## Success Criteria

- [ ] Messages are produced to and consumed from the Kafka topic.
- [ ] Transient failures are retried up to 3 times with exponential backoff.
- [ ] `MalformedOrderException` is routed directly to the dead-letter topic (no retries).
- [ ] The embedded Kafka broker starts automatically.
