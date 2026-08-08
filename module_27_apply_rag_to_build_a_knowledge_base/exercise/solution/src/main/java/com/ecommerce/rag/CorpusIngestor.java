package com.ecommerce.rag;

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
public class CorpusIngestor {

    private final VectorStore vectorStore;

    @Value("classpath:product-manual.txt")
    private Resource manualResource;

    public CorpusIngestor(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void ingestOnStartup() throws IOException {
        String content = manualResource.getContentAsString(StandardCharsets.UTF_8);

        // Split by blank lines — each section is a logical unit
        String[] sections = content.split("\\n\\n+");
        List<Document> documents = new ArrayList<>();
        for (String section : sections) {
            String trimmed = section.trim();
            if (!trimmed.isEmpty()) {
                documents.add(new Document(trimmed, Map.of("source", "product-manual.txt")));
            }
        }

        vectorStore.add(documents);
        System.out.println("Product Manual ingested. Sections added: " + documents.size());
    }
}
