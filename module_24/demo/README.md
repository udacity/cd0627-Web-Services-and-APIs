# Module 24 - Semantic Search (Vector Stores)

## Demo Walkthrough

In this demo, we explore Semantic Search. We convert textual documents into mathematical vectors using an `EmbeddingModel` and store them in a `VectorStore` for similarity searches.

### `SemanticDemoRunner.java` — Core Implementation

```java
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

    // ...
}
```

### Execution Workflow

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `VectorStore` | Load your documents into the `VectorStore` during initialization. |
| 2 | `SearchRequest` | In your search endpoint, construct a `SearchRequest`. |
| 3 | `vectorStore.similaritySearch(request)` | Use `vectorStore.similaritySearch(request)` to find the top matching documents. |


### Expected Output

```
Application started successfully.
Expected API behaviors active.
```

### Key Concepts Demonstrated
- **Embeddings and Vectorization**
- **Cosine Similarity Search**
- **Spring AI `SimpleVectorStore`**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
