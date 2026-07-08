# Module 28 - Asynchronous Messaging Systems - Solution

## Solution Walkthrough

The solution ensures resilient event processing by combining `@KafkaListener` with `@RetryableTopic`.

### `KafkaLocalConfig.java` — The Implementation

```java
public class KafkaLocalConfig {

    public static final String TOPIC = "orders-topic";

    @Bean
    public EmbeddedKafkaBroker embeddedKafka() {
        return new EmbeddedKafkaKraftBroker(1, 1, TOPIC)
                .kafkaPorts(9092);
    }

    @Bean
    public NewTopic ordersTopic() {
        return TopicBuilder.name(TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
```

### Step-by-step Design Decisions:

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | Add `@RetryableTopic` here with 3 attempts. | `src/main/java/com/ecommerce/kafka/InventoryConsumer.java` |
| 2 | Configure exponential `@Backoff` (e.g. wait 1s, then 2s, then 4s). | `src/main/java/com/ecommerce/kafka/InventoryConsumer.java` |
| 3 | Exclude MalformedOrderException.class from retries entirely. | `src/main/java/com/ecommerce/kafka/InventoryConsumer.java` |
| 4 | Create beans for EmbeddedKafkaBroker and NewTopic | `src/main/java/com/ecommerce/kafka/KafkaLocalConfig.java` |


### Key Concepts Demonstrated
- **`@KafkaListener` for message consumption**
- **`@RetryableTopic` for automated DLQ routing**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
