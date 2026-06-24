package com.ecommerce.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/api/chat/simple")
    public String simpleChat() {
        return chatClient.prompt()
                .system("You are a helpful assistant")
                .user("Hello")
                .call()
                .content();
    }

    @GetMapping("/api/chat/template")
    public String templateChat(@RequestParam String input) {
        return chatClient.prompt()
                .system(s -> s.text("Analyze this: {data}").param("data", input))
                .user("Tell me about it")
                .call()
                .content();
    }

    @GetMapping("/api/chat/tool")
    public String toolChat() {
        return chatClient.prompt()
                .user("What is today's date?")
                .functions("getCurrentDate")
                .call()
                .content();
    }
}
