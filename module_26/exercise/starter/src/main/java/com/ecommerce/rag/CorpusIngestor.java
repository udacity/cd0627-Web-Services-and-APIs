package com.ecommerce.rag;

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
public class CorpusIngestor {

    private final VectorStore vectorStore;

    @Value("classpath:product-manual.txt")
    private Resource manualResource;

    public CorpusIngestor(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void ingestOnStartup() {
        TextReader textReader = new TextReader(manualResource);
        textReader.getCustomMetadata().put("source", "product-manual.txt");
        List<Document> documents = textReader.get();

        TokenTextSplitter splitter = new TokenTextSplitter();
        List<Document> chunks = splitter.apply(documents);

        vectorStore.add(chunks);
        System.out.println("Product Manual ingested into PGVector.");
    }
}
