# Module 25 — Vector Databases and Semantic Search — Exercise Instructions

## Exercise Overview

Keyword search isn't cutting it for your FAQ system. You need to implement Semantic Search by converting documents into vector embeddings and querying a `VectorStore` based on conceptual similarity.

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

1. In `src/main/java/com/ecommerce/search/FaqController.java`:
   - Create a `SearchRequest` with the given query (Step 1).
   - Set `topK` to 3 (Step 2).
   - If the `category` parameter is provided, add a filter expression: `"category == '" + category + "'"` (Step 3).

2. In `src/main/java/com/ecommerce/search/FaqIngestor.java`:
   - Use `TextReader` to read the FAQ resource (Step 4).
   - Pass the document through a `TokenTextSplitter` to create chunks (Step 5).
   - For each chunk, inject metadata based on text content (Step 6).
   - Save the chunked documents to the `SimpleVectorStore` (Step 7).

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
curl "http://localhost:8080/search?query=forgot+my+credentials"
```

---

## Success Criteria

- [ ] Searching for "forgot credentials" finds the FAQ about "password reset" (semantic match, not keyword match).
- [ ] Documents are automatically ingested into the vector store on startup.
- [ ] The `topK` parameter limits results to 3.
