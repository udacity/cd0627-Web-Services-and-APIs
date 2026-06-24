package com.ecommerce.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.function.Function;

@Configuration
public class AiConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }

    @Bean
    public Function<Request, Response> getCurrentDate() {
        return request -> new Response(LocalDate.now().toString());
    }

    public record Request() {}
    public record Response(String date) {}
}
