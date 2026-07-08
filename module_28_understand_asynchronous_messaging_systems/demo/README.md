# Module 28 - Asynchronous Messaging Systems

## Demo Walkthrough

This demo introduces asynchronous messaging with Spring Kafka and tackles failure scenarios using DLQs.

### `KafkaLocalConfig.java` — Core Implementation

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

### Key Concepts Demonstrated
- **`@KafkaListener` for message consumption**
- **`@RetryableTopic` for automated DLQ routing**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
