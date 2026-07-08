package com.ecommerce.search;

import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
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
        // TODO (Step 4): Use TextReader to read 'faqResource'.
        // TODO (Step 5): Pass the document through a TokenTextSplitter to create chunks.
        // TODO (Step 6): For each chunk, manually inject metadata based on text content:
        //       e.g., if text contains "IT" or "password", add Map.of("category", "IT")
        //       else if text contains "HR" or "vacation", add Map.of("category", "HR")
        // TODO (Step 7): Save the chunked documents to the SimpleVectorStore.
        System.out.println("FAQ Ingestion started (implement me!)...");
    }
}
