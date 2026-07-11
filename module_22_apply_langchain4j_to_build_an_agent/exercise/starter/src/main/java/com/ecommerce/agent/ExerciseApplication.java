package com.ecommerce.agent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExerciseApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExerciseApplication.class, args);
    }

    // TODO (Step 4): Add a @Bean method that returns a ChatMemoryProvider.
    // It should return memoryId -> MessageWindowChatMemory.withMaxMessages(10);
    // You will need to import org.springframework.context.annotation.Bean
}