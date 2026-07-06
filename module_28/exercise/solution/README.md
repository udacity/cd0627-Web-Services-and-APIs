# Module 28 - Spring Kafka and DLQs - Solution

## Solution Walkthrough

The solution ensures resilient event processing. By combining `@KafkaListener` with `@RetryableTopic`, transient exceptions are retried, while exhausted or fatal messages are routed safely to a Dead Letter Topic.

### `KafkaLocalConfig.java` — The Implementation

```java
@Bean
    public EmbeddedKafkaBroker embeddedKafka() {
        return new EmbeddedKafkaKraftBroker(1, 1, TOPIC)
                .kafkaPorts(9092);
    }
```

### Step-by-step Design Decisions:

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `@KafkaListener` | Annotate your consumer method with `@KafkaListener`. |
| 2 | `@RetryableTopic` | Add `@RetryableTopic` to automatically route failures to a backoff topic. |
| 3 | Step 3 | Exclude specific fatal exceptions from being retried. |


### Expected Output

```
══════════════════════════════════════════
 Integration Successful 
══════════════════════════════════════════
```

### Key Concepts Demonstrated
- **`@KafkaListener` for message consumption**
- **`@RetryableTopic` for automated DLQ routing**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
