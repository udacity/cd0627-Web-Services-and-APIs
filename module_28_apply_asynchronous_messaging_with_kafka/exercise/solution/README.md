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

1. In `src/main/java/com/ecommerce/kafka/InventoryConsumer.java`, annotate your consumer method with `@KafkaListener`.
2. Add `@RetryableTopic` to automatically route failures to a backoff topic.


### Key Concepts Demonstrated
- **`@KafkaListener` for message consumption**
- **`@RetryableTopic` for automated DLQ routing**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
