# Module 26 - Retrieval-Augmented Generation (RAG) - Exercise Instructions

## Exercise Overview

You want the AI to answer customer support questions, but it hallucinates answers. You must implement the RAG pattern to ground the AI in your specific documents.

---

## Prerequisites
- **Java 21+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

> [!NOTE]
> **Deep Dive:** The `QuestionAnswerAdvisor` automatically intercepts the LLM call. Before the request goes to the LLM, the Advisor takes the user's prompt, embeds it into a vector, performs a cosine similarity search against the provided `VectorStore`, extracts the matching documents, and injects them into the prompt's context window. This ensures the LLM grounds its answers in your data.

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | Create a bean for SimpleVectorStore | `src/main/java/com/ecommerce/rag/RagConfig.java` |
| 2 | Configure QuestionAnswerAdvisor with a custom SearchRequest (.topK(2).similarityThreshold(0.80)) | `src/main/java/com/ecommerce/rag/SupportOracleController.java` |
| 3 | Inject a strict system prompt regarding out-of-scope questions | `src/main/java/com/ecommerce/rag/SupportOracleController.java` |
| 4 | Execute prompt, return answer and extract sources from context metadata | `src/main/java/com/ecommerce/rag/SupportOracleController.java` |


> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] The AI accurately answers questions based on your documents.
- [ ] The AI does not hallucinate facts outside the provided context.
