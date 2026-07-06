package com.ecommerce.search;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SemanticDemoRunner implements CommandLineRunner {

    private final EmbeddingModel embeddingModel;
    private final VectorStore vectorStore;

    public SemanticDemoRunner(EmbeddingModel embeddingModel, VectorStore vectorStore) {
        this.embeddingModel = embeddingModel;
        this.vectorStore = vectorStore;
    }

    @Override
    public void run(String... args) {
        System.out.println("=== Step 1: Raw Embeddings ===");
        float[] vector = embeddingModel.embed("Hello World");
        System.out.println("Embedded 'Hello World' -> vector size: " + vector.length);
        if (vector.length > 5) {
            System.out.printf("First 5 dimensions: [%f, %f, %f, %f, %f]...%n", 
                vector[0], vector[1], vector[2], vector[3], vector[4]);
        }

        System.out.println("\n=== Step 2: Populating VectorStore ===");
        List<Document> documents = List.of(
            new Document("The cat sat on the mat"),
            new Document("A dog barked loudly"),
            new Document("Felines prefer to rest on rugs")
        );
        vectorStore.add(documents);
        System.out.println("Added 3 documents to SimpleVectorStore.");

        System.out.println("\n=== Step 3: Semantic Search ===");
        String query = "Where do kittens sleep?";
        System.out.println("Query: " + query);
        
        List<Document> results = vectorStore.similaritySearch(SearchRequest.builder().query(query).topK(1).build());
        if (!results.isEmpty()) {
            System.out.println("Top Match: " + results.get(0).getText());
            System.out.println("(Notice it matched without any exact keyword overlap!)");
        }
    }
}
