# Module 24 - Vector Databases and Semantic Search

## Demo Walkthrough

In this demo, we explore Semantic Search. We convert textual documents into mathematical vectors using an `EmbeddingModel`.

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

        System.out.println("\n=== Step 2: Populating VectorStore ===");
        List<Document> documents = List.of(
            new Document("The cat sat on the mat"),
            new Document("A dog barked loudly"),
            new Document("Felines prefer to rest on rugs")
    // ...
}
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
