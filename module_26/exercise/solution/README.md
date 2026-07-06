# Module 26 - RAG (Retrieval-Augmented Generation) - Solution

## Solution Walkthrough

The solution implements a robust RAG pipeline. The `QuestionAnswerAdvisor` intercepts the prompt, performs a semantic search, injects the retrieved documents into the context, and routes it to the LLM.

### `CorpusIngestor.java` — The Implementation

```java
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
```

### Step-by-step Design Decisions:

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `ChatClient` | Configure the `ChatClient` with a `QuestionAnswerAdvisor`. |
| 2 | `VectorStore` | Pass the `VectorStore` and a `SearchRequest` to the advisor. |
| 3 | Step 3 | Define a strict system prompt instructing the AI to only use the provided context. |


### Expected Output

```
══════════════════════════════════════════
 Integration Successful 
══════════════════════════════════════════
```

### Key Concepts Demonstrated
- **Retrieval-Augmented Generation (RAG)**
- **Spring AI `QuestionAnswerAdvisor`**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
