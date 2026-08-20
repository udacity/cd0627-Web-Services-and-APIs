# Module 27 — Retrieval-Augmented Generation (RAG) — Exercise Instructions

## Exercise Overview

You want the AI to answer customer support questions, but it hallucinates answers. You must implement the RAG pattern to ground the AI in your specific documents by ingesting a product manual into a vector store and configuring the `ChatClient` with a `QuestionAnswerAdvisor`.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**
- **OpenAI API key** — Set the environment variable before running:
  ```bash
  export OPENAI_API_KEY=<your-api-key>
  ```
  The default base URL is `https://openai.vocareum.com`. Override with:
  ```bash
  export SPRING_AI_OPENAI_BASE_URL=https://api.openai.com
  ```

---

## Step-by-Step Implementation Guide

### Document Ingestion (`CorpusIngestor.java`)

1. Read the manual resource content as a String (Step 1).
2. Split the content by blank lines into logical sections (Step 2).
3. Create a `Document` for each section with metadata `Map.of("source", "product-manual.txt")` (Step 3).
4. Add all documents to the `VectorStore` (Step 4).

### ChatClient Pipeline (`SupportOracleController.java`)

5. Configure a `QuestionAnswerAdvisor` using a custom `SearchRequest` with `.topK(2)` and `.similarityThreshold(0.50)` (Step 5).
6. Inject a **strict system prompt** that instructs the AI to reply with "I do not have enough information" when the context doesn't contain the answer (Step 6).
7. In the `ask` method, execute the prompt and return a `RagResponse` containing the answer and source metadata (Step 7).

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

```bash
OPENAI_API_KEY=<your-key> mvn spring-boot:run
```

Test with:
```bash
curl "http://localhost:8080/ask?question=How+do+I+calibrate+the+SuperWidget"
```

---

## Success Criteria

- [ ] The AI accurately answers questions based on the product manual (e.g., "Press the green button for 3 seconds to auto-calibrate").
- [ ] The AI responds with "I do not have enough information" for questions outside the provided context.
- [ ] The `RagResponse` includes the answer and source metadata.
