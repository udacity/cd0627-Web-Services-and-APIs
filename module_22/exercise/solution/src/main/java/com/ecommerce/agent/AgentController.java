package com.ecommerce.agent;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AgentController {
    private final ChatClient chatClient;

    public AgentController(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("You are an expert travel assistant. You have access to tools to check flights and weather.")
                .defaultTools("checkFlight", "getWeather")
                .build();
    }

    @GetMapping("/ask")
    public String ask(@RequestParam String message) {
        return chatClient.prompt().user(message).call().content();
    }
}
