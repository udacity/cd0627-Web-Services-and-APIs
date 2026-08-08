package com.ecommerce.rag;

import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class CorpusIngestor {

    private final VectorStore vectorStore;

    @Value("classpath:product-manual.txt")
    private Resource manualResource;

    public CorpusIngestor(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void ingestOnStartup() {
        // TODO (Step 1): Read the manual resource content as a String.
        // TODO (Step 2): Split the content by blank lines into logical sections.
        // TODO (Step 3): Create a Document for each section with metadata Map.of("source", "product-manual.txt").
        // TODO (Step 4): Add all documents to the VectorStore.
        System.out.println("Product Manual ingestion started (implement me!)...");
    }
}
