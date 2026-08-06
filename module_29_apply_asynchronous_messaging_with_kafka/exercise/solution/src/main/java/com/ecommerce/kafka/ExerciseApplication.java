package com.ecommerce.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.test.EmbeddedKafkaKraftBroker;

@SpringBootApplication
public class ExerciseApplication {

    public static void main(String[] args) {
        // Start embedded Kafka BEFORE Spring context to ensure bootstrap-servers is set
        EmbeddedKafkaKraftBroker broker = new EmbeddedKafkaKraftBroker(1, 1, "order-events");
        broker.afterPropertiesSet();
        String brokers = broker.getBrokersAsString();
        System.setProperty("spring.kafka.bootstrap-servers", brokers);
        System.out.println("Embedded Kafka started at: " + brokers);

        Runtime.getRuntime().addShutdownHook(new Thread(broker::destroy));

        SpringApplication.run(ExerciseApplication.class, args);
    }
}
