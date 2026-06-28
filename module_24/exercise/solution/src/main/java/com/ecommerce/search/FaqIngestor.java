package com.ecommerce.search;

import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

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
        TextReader textReader = new TextReader(faqResource);
        textReader.getCustomMetadata().put("source", "company-faq.txt");
        List<Document> documents = textReader.get();

        TokenTextSplitter splitter = new TokenTextSplitter();
        List<Document> chunks = splitter.apply(documents);

        for (Document chunk : chunks) {
            String text = chunk.getContent().toLowerCase();
            if (text.contains("it") || text.contains("password") || text.contains("laptop")) {
                chunk.getMetadata().put("category", "IT");
            } else if (text.contains("hr") || text.contains("vacation") || text.contains("payroll")) {
                chunk.getMetadata().put("category", "HR");
            } else {
                chunk.getMetadata().put("category", "GENERAL");
            }
        }

        vectorStore.add(chunks);
        System.out.println("FAQ Ingestion completed. Chunks added: " + chunks.size());
    }
}
