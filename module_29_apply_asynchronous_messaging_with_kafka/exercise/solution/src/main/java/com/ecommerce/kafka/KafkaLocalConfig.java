package com.ecommerce.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Kafka configuration. The embedded broker is started in ExerciseApplication.main()
 * before the Spring context, so bootstrap-servers is already set.
 */
@Configuration
public class KafkaLocalConfig {

    public static final String TOPIC = "order-events";

    @Bean
    public NewTopic ordersTopic() {
        return TopicBuilder.name(TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
