# Module 28 - Spring Kafka and DLQs - Exercise Instructions

## Exercise Overview

Your event-driven architecture is failing because poisoned messages are causing consumers to crash repeatedly, blocking the partition. You must implement retries and a Dead Letter Queue (DLQ).

---

## Prerequisites
- **Java 25**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Step 1
Annotate your consumer method with `@KafkaListener`.

### Step 2
Add `@RetryableTopic` to automatically route failures to a backoff topic.

### Step 3
Exclude specific fatal exceptions from being retried.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] Transient errors are retried successfully.
- [ ] Fatal errors (e.g. malformed JSON) are sent immediately to the DLT (Dead Letter Topic).
