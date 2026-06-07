package com.ecommerce.rma.config;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
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
     * Classpath resource containing the company's return-policy rules.
     * Loaded from {@code src/main/resources/policy-seed.txt}.
     */
    @Value("classpath:policy-seed.txt")
    private Resource policyFile;

    /**
     * The VectorStore bean defined in this class is injected back here by Spring
     * so the {@code @PostConstruct} method can seed it on startup.
     *
     * <p>Spring handles this correctly via CGLIB proxy on {@code @Configuration}
     * classes — it will inject the same singleton bean instance.
     */
    @Autowired
    private VectorStore vectorStore;

    // =========================================================================
    // TODO (Step 4 – Part A): Confirm the VectorStore bean below.
    //
    // The SimpleVectorStore is already instantiated for you. Review the code and
    // understand what EmbeddingModel does:
    //   - EmbeddingModel is auto-configured by the Spring AI OpenAI starter.
    //   - It calls the OpenAI /embeddings endpoint to convert text → float[].
    //   - SimpleVectorStore stores those float arrays in a Java List in memory.
    //
    // You do NOT need to change this bean.
    // =========================================================================

    /**
     * Creates the in-memory {@link VectorStore} backed by OpenAI's text-embedding model.
     *
     * @param embeddingModel auto-configured by {@code spring-ai-openai-spring-boot-starter}
     * @return a {@link SimpleVectorStore} that holds policy documents as embeddings
     */
    @Bean
    public VectorStore vectorStore(EmbeddingModel embeddingModel) {
        return SimpleVectorStore.builder(embeddingModel).build();
    }

    // =========================================================================
    // TODO (Step 4 – Part B): Complete the seedVectorStore() method below.
    //
    // Goal: Read the text from `policyFile` and store it in the VectorStore so
    // the QuestionAnswerAdvisor can retrieve it during the policy check.
    //
    // Steps to implement:
    //  1. Read the file content into a String:
    //       String policyText = policyFile.getContentAsString(StandardCharsets.UTF_8);
    //
    //  2. Wrap the text in a Spring AI Document object:
    //       Document doc = new Document(policyText);
    //
    //  3. Add the document to the VectorStore — this will call OpenAI's embedding
    //     API and store the resulting vector in memory:
    //       vectorStore.add(List.of(doc));
    //
    // Hint: The method signature already declares `throws IOException` to handle
    // the file-reading operation. Make sure to import:
    //   - org.springframework.ai.document.Document
    //   - java.util.List
    //   - java.nio.charset.StandardCharsets
    // =========================================================================

    /**
     * Seeds the {@link VectorStore} with the company return-policy document on
     * application startup, before any requests are processed.
     *
     * <p>{@code @PostConstruct} is called by Spring after all fields are injected
     * and the bean is fully initialized, making it safe to use {@code vectorStore}
     * and {@code policyFile} here.
     *
     * @throws IOException if the policy file cannot be read from the classpath
     */
    @PostConstruct
    public void seedVectorStore() throws IOException {
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