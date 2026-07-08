# Module 26 - Retrieval-Augmented Generation (RAG)

## Demo Walkthrough

This demo combines Chat Models and Vector Stores to implement RAG.

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

### Key Concepts Demonstrated
- **Retrieval-Augmented Generation (RAG)**
- **Spring AI `QuestionAnswerAdvisor`**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
