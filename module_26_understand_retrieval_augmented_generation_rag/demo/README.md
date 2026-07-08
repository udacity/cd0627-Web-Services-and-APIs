# Module 26 - RAG (Retrieval-Augmented Generation)

## Demo Walkthrough

This demo combines Chat Models and Vector Stores to implement RAG. We use `QuestionAnswerAdvisor` to automatically query the `VectorStore` for relevant context before sending the prompt to the LLM.

### `RagRunner.java` — Core Implementation

```java
public class RagRunner implements CommandLineRunner {

    private final ChatClient chatClient;

    public RagRunner(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder
            .defaultAdvisors(QuestionAnswerAdvisor.builder(vectorStore).build())
            .build();
    }

    @Override
    public void run(String... args) {
        System.out.println("=== Step 3: The Execution ===");
        String response = chatClient.prompt()
            .user("What is the main advantage of semantic search?")
            .call()
            .content();
        System.out.println("Agent Response: " + response);
    }
}
```

### Execution Workflow

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `ChatClient` | Configure the `ChatClient` with a `QuestionAnswerAdvisor`. |
| 2 | `VectorStore` | Pass the `VectorStore` and a `SearchRequest` to the advisor. |
| 3 | Step 3 | Define a strict system prompt instructing the AI to only use the provided context. |


### Expected Output

```
Application started successfully.
Expected API behaviors active.
```

### Key Concepts Demonstrated
- **Retrieval-Augmented Generation (RAG)**
- **Spring AI `QuestionAnswerAdvisor`**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
