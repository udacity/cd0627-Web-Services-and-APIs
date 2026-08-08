package com.ecommerce.search;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class FaqIngestor {

    private final VectorStore vectorStore;

    @Value("classpath:company-faq.txt")
    private Resource faqResource;

    public FaqIngestor(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void ingestOnStartup() throws IOException {
        String content = faqResource.getContentAsString(StandardCharsets.UTF_8);

        // Split by blank lines — each FAQ entry is a question + answer pair
        String[] entries = content.split("\\n\\n+");

        List<Document> documents = new ArrayList<>();
        for (String entry : entries) {
            String trimmed = entry.trim();
            if (trimmed.isEmpty()) continue;

            // Auto-detect category from content
            String lower = trimmed.toLowerCase();
            String category;
            if (lower.contains("password") || lower.contains("laptop") || lower.contains("it portal") || lower.contains("helpdesk")) {
                category = "IT";
            } else if (lower.contains("vacation") || lower.contains("payroll") || lower.contains("hr")) {
                category = "HR";
            } else {
                category = "GENERAL";
            }

            documents.add(new Document(trimmed, Map.of("source", "company-faq.txt", "category", category)));
        }

        vectorStore.add(documents);
        System.out.println("FAQ Ingestion completed. Documents added: " + documents.size());
    }
}
