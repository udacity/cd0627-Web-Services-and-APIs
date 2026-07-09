# Module 26 - Retrieval-Augmented Generation (RAG) - Exercise Instructions

## Exercise Overview

You want the AI to answer customer support questions, but it hallucinates answers. You must implement the RAG pattern to ground the AI in your specific documents.

---

## Prerequisites
- **Java 23+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task |
|------|-----------|
| 1 | In `src/main/java/com/ecommerce/rag/RagConfig.java`, configure the `ChatClient` with a `QuestionAnswerAdvisor`. |
| 2 | Pass the `VectorStore` to the advisor so it can perform semantic searches automatically. |


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
