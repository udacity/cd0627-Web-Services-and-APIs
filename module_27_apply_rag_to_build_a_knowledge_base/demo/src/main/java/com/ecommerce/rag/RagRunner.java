package com.ecommerce.rag;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RagRunner implements CommandLineRunner {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    @Value("classpath:product-manual.txt")
    private Resource productManual;

    public RagRunner(ChatClient.Builder builder, VectorStore vectorStore) {
        this.vectorStore = vectorStore;
        this.chatClient = builder
            .defaultAdvisors(QuestionAnswerAdvisor.builder(vectorStore).build())
            .build();
    }

    @Override
    public void run(String... args) throws Exception {
        // Step 1: Ingest the knowledge base into the vector store
        System.out.println("=== Step 1: Ingesting Knowledge Base ===");
        String content = productManual.getContentAsString(StandardCharsets.UTF_8);

        // Split by blank lines into logical sections
        String[] sections = content.split("\\n\\n+");
        List<Document> documents = new ArrayList<>();
        for (String section : sections) {
            String trimmed = section.trim();
            if (!trimmed.isEmpty()) {
                documents.add(new Document(trimmed, Map.of("source", "product-manual.txt")));
            }
        }
        vectorStore.add(documents);
        System.out.println("Ingested " + documents.size() + " document sections.");

        // Step 2: Ask WITH RAG — the QuestionAnswerAdvisor retrieves relevant context
        System.out.println("\n=== Step 2: RAG Query — Semantic Search ===");
        String response = chatClient.prompt()
            .user("What is the main advantage of semantic search?")
            .call()
            .content();
        System.out.println("Agent Response: " + response);

        // Step 3: Ask another question to show it works across different topics
        System.out.println("\n=== Step 3: RAG Query — Warranty ===");
        String warrantyResponse = chatClient.prompt()
            .user("What is the warranty policy for the Widget Pro?")
            .call()
            .content();
        System.out.println("Agent Response: " + warrantyResponse);
    }
}
