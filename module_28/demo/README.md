# Module 28 - Spring Kafka and DLQs

## Demo Walkthrough

This demo introduces asynchronous messaging with Spring Kafka. We tackle failure scenarios by showcasing Dead Letter Queues (DLQs) to prevent toxic messages from blocking the partition.

### `KafkaLocalConfig.java` — Core Implementation

```java
@Bean
    public EmbeddedKafkaBroker embeddedKafka() {
        return new EmbeddedKafkaKraftBroker(1, 1, TOPIC)
                .kafkaPorts(9092);
    }
```

### Execution Workflow

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `@KafkaListener` | Annotate your consumer method with `@KafkaListener`. |
| 2 | `@RetryableTopic` | Add `@RetryableTopic` to automatically route failures to a backoff topic. |
| 3 | Step 3 | Exclude specific fatal exceptions from being retried. |


### Expected Output

```
Application started successfully.
Expected API behaviors active.
```

### Key Concepts Demonstrated
- **`@KafkaListener` for message consumption**
- **`@RetryableTopic` for automated DLQ routing**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
