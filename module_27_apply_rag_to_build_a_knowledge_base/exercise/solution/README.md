# Module 27 - Retrieval-Augmented Generation (RAG) - Solution

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
        System.out.println("Product Manual ingested into SimpleVectorStore.");
    }
}
```

### Step-by-step Design Decisions:

1. In `src/main/java/com/ecommerce/rag/RagConfig.java`, configure the `ChatClient` with a `QuestionAnswerAdvisor`.
2. Pass the `VectorStore` to the advisor so it can perform semantic searches automatically.


### Key Concepts Demonstrated
- **Retrieval-Augmented Generation (RAG)**
- **Spring AI `QuestionAnswerAdvisor`**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
