package com.ecommerce.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.EmbeddedKafkaKraftBroker;

@Configuration
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
