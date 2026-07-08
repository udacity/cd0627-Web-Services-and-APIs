# Module 26 - Retrieval-Augmented Generation (RAG) - Solution

## Solution Walkthrough

The solution implements a robust RAG pipeline. The `QuestionAnswerAdvisor` intercepts the prompt, performs a semantic search, injects documents, and routes to the LLM.

### `CorpusIngestor.java` — The Implementation

```java
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
```

### Step-by-step Design Decisions:

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | Create a bean for SimpleVectorStore | `src/main/java/com/ecommerce/rag/RagConfig.java` |
| 2 | Configure QuestionAnswerAdvisor with a custom SearchRequest (.topK(2).similarityThreshold(0.80)) | `src/main/java/com/ecommerce/rag/SupportOracleController.java` |
| 3 | Inject a strict system prompt regarding out-of-scope questions | `src/main/java/com/ecommerce/rag/SupportOracleController.java` |
| 4 | Execute prompt, return answer and extract sources from context metadata | `src/main/java/com/ecommerce/rag/SupportOracleController.java` |


### Key Concepts Demonstrated
- **Retrieval-Augmented Generation (RAG)**
- **Spring AI `QuestionAnswerAdvisor`**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
