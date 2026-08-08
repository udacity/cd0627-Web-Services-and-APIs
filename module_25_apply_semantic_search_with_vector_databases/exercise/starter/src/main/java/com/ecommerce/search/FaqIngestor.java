package com.ecommerce.search;

import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class FaqIngestor {

    private final VectorStore vectorStore;

    @Value("classpath:company-faq.txt")
    private Resource faqResource;

    public FaqIngestor(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void ingestOnStartup() {
        // TODO (Step 4): Read the FAQ resource content as a String.
        // TODO (Step 5): Split the content by blank lines (each FAQ entry is a question + answer pair).
        // TODO (Step 6): For each entry, create a Document with metadata:
        //       - Detect category from text content (e.g., "password" or "laptop" → "IT",
        //         "vacation" or "payroll" → "HR", else → "GENERAL")
        //       - Use Map.of("source", "company-faq.txt", "category", detectedCategory)
        // TODO (Step 7): Add all documents to the VectorStore.
        System.out.println("FAQ Ingestion started (implement me!)...");
    }
}
