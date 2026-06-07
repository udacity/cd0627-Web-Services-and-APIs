package com.ecommerce.rma.config;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Configures the Spring AI infrastructure: the {@link VectorStore} used for
 * Retrieval-Augmented Generation (RAG) in the return-policy advisor.
 *
 * <h2>What is a VectorStore?</h2>
 * A {@link VectorStore} stores text as <em>vector embeddings</em> — high-dimensional
 * numerical representations produced by an embedding model. When you search it with a
 * natural-language query, it finds the most semantically similar stored documents.
 * In this project, the {@code policy-seed.txt} file is loaded into the store so the
 * AI can perform <b>policy-aware RAG</b> in Step 4.
 *
 * <h2>What is SimpleVectorStore?</h2>
 * {@link SimpleVectorStore} is a fully in-memory, zero-dependency implementation
 * provided by Spring AI. It is perfect for prototypes and learner projects — no
 * Pinecone, Weaviate, or other external database needed.
 */
@Configuration
public class AiConfig {

    /**
     * Creates the in-memory {@link VectorStore} backed by OpenAI's text-embedding model
     * and seeds it with the return-policy document on startup.
     *
     * @param embeddingModel auto-configured by {@code spring-ai-starter-model-openai}
     * @param policyFile     classpath resource containing the company return-policy rules
     * @return a {@link SimpleVectorStore} that holds policy documents as embeddings
     */
    @Bean
    public VectorStore vectorStore(EmbeddingModel embeddingModel,
                                   @Value("classpath:policy-seed.txt") Resource policyFile) throws IOException {
        VectorStore store = SimpleVectorStore.builder(embeddingModel).build();
        seedVectorStore(store, policyFile);
        return store;
    }

    /**
     * Seeds the {@link VectorStore} with the company return-policy document.
     * Skipped when no real OpenAI API key is configured (offline / mock mode).
     */
    private void seedVectorStore(VectorStore vectorStore, Resource policyFile) throws IOException {
        String apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.trim().isEmpty() || "mock-key".equalsIgnoreCase(apiKey.trim())) {
            System.out.println("[AiConfig] OPENAI_API_KEY is not set or is 'mock-key'. Skipping vector store seeding to allow offline/mock runs.");
            return;
        }

        String policyText = policyFile.getContentAsString(StandardCharsets.UTF_8);
        Document doc = new Document(policyText);
        vectorStore.add(List.of(doc));
        System.out.println("[AiConfig] VectorStore successfully seeded with policy documents.");
    }
}
