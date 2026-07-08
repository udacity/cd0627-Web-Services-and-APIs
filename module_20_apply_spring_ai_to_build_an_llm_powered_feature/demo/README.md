# Module 20 - Spring AI Framework

## Demo Walkthrough

In this demo, we introduce Spring AI and the `ChatClient` to execute simple prompts against an LLM.

### `AiConfig.java` — Core Implementation

```java
public class AiConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }

    @Bean
    public Function<Request, Response> getCurrentDate() {
        return request -> new Response(LocalDate.now().toString());
    }

    public record Request() {}
    public record Response(String date) {}
}
```

### Key Concepts Demonstrated
- **Spring AI `ChatClient`**
- **System vs User Prompts**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
