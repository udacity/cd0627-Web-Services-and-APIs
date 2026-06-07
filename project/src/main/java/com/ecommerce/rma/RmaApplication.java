package com.ecommerce.rma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point for the AI-Powered RMA Engine.
 *
 * <p>{@code @SpringBootApplication} is a convenience annotation that combines:
 * <ul>
 *   <li>{@code @Configuration}  – marks this as a Spring configuration source</li>
 *   <li>{@code @EnableAutoConfiguration} – enables Spring Boot's auto-configuration
 *       (Kafka, GraphQL, Spring AI, etc. are all wired up automatically)</li>
 *   <li>{@code @ComponentScan} – scans the {@code com.ecommerce.rma} package tree
 *       for @Service, @Controller, @Repository, and @Configuration beans</li>
 * </ul>
 *
 * <p><b>No changes needed in this file.</b> Your work starts in the other classes.
 */
@SpringBootApplication
public class RmaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RmaApplication.class, args);
    }
}
