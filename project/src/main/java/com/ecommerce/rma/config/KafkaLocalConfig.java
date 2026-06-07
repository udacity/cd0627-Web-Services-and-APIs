package com.ecommerce.rma.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Configures an <b>in-memory Kafka broker</b> so the application can produce and
 * consume Kafka messages without Docker or any external infrastructure.
 *
 * <p><b>How it works:</b>
 * {@link EmbeddedKafkaBroker} (from {@code spring-kafka-test}) starts a real KRaft
 * Kafka broker inside the JVM when the Spring context initialises. It binds to
 * {@code localhost:9092}, which matches the value in {@code application.properties}
 * ({@code spring.kafka.bootstrap-servers=localhost:9092}).
 *
 * <p><b>No changes needed in this file.</b>
 * The broker and topic are fully configured below. Your Kafka work lives in
 * {@code RmaService} (producer) and {@code DashboardService} (consumer).
 *
 * <p><b>Note for learners:</b>
 * In a real production system you would connect to an external Kafka cluster (e.g.
 * on Confluent Cloud or AWS MSK) by changing {@code spring.kafka.bootstrap-servers}
 * and removing this class. The embedded broker is only used here to keep the project
 * self-contained.
 */
@Configuration
public class KafkaLocalConfig {

    /** Name of the Kafka topic used throughout the application. */
    public static final String RETURNS_TOPIC = "returns-topic";

    /**
     * Starts a single-node in-memory Kafka KRaft broker on port 9092.
     *
     * <p>The broker is created with one partition for {@code "returns-topic"}.
     * Spring Boot will use this broker when auto-configuring the
     * {@code KafkaTemplate} producer and the {@code @KafkaListener} consumer.
     */
    @Bean
    public org.springframework.kafka.test.EmbeddedKafkaBroker embeddedKafka() {
        return new org.springframework.kafka.test.EmbeddedKafkaKraftBroker(1, 1, RETURNS_TOPIC)
                .kafkaPorts(9092);
    }

    /**
     * Declares the {@code "returns-topic"} as a {@link NewTopic} bean so that
     * Spring Boot's {@code KafkaAdmin} creates it on startup if it does not already
     * exist.  Having it here also makes it easy to adjust partition count / replication
     * factor in one place.
     */
    @Bean
    public NewTopic returnsTopic() {
        return TopicBuilder.name(RETURNS_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }

    /**
     * Producer factory for JSON-serialised return events ({@code ReturnApprovedEvent}).
     * Uses the embedded broker address so tests and runtime share the same configuration.
     */
    @Bean
    public ProducerFactory<String, Object> producerFactory(
            org.springframework.kafka.test.EmbeddedKafkaBroker embeddedKafka) {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, embeddedKafka.getBrokersAsString());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, true);
        return new DefaultKafkaProducerFactory<>(config);
    }

    /**
     * Typed Kafka template used by {@link com.ecommerce.rma.service.RmaService} to publish
     * {@link com.ecommerce.rma.event.ReturnApprovedEvent} payloads as JSON.
     */
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}