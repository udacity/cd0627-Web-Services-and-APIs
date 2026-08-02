package com.ecommerce.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RagRunner implements CommandLineRunner {

    private final ChatClient chatClient;

    public RagRunner(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder
            .defaultAdvisors(QuestionAnswerAdvisor.builder(vectorStore).build())
            .build();
    }

    @Override
    public void run(String... args) {
        System.out.println("=== Step 3: The Execution ===");
        String response = chatClient.prompt()
            .user("What is the main advantage of semantic search?")
            .call()
            .content();
        System.out.println("Agent Response: " + response);
    }
}
