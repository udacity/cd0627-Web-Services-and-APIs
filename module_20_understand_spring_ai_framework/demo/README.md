# Module 20 - Spring AI - ChatClient

## Demo Walkthrough

In this demo, we introduce Spring AI and the `ChatClient`. We demonstrate how to execute simple prompts against a Large Language Model (LLM) to generate dynamic textual responses.

### `AiConfig.java` — Core Implementation

```java
@Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }
```

### Execution Workflow

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `ChatClient.Builder` | Inject `ChatClient.Builder` into your controller. |
| 2 | `ChatClient` | Build the `ChatClient` with a default system prompt. |
| 3 | `chatClient.prompt().user(message).call().content()` | Call `chatClient.prompt().user(message).call().content()` to get the AI's response. |


### Expected Output

```
Application started successfully.
Expected API behaviors active.
```

### Key Concepts Demonstrated
- **Spring AI `ChatClient`**
- **System vs User Prompts**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
