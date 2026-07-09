# Module 22 - LangChain4j for Java

## Demo Walkthrough

This demo explores building autonomous agents using LangChain4j, focusing on giving the LLM the ability to invoke local Java methods.

### `TravelAssistant.java` — Core Implementation

```java
@AiService
public interface TravelAssistant {
    @SystemMessage("You are an expert travel assistant. You have access to tools to check flights and weather.")
    String chat(String userMessage);
}
```

### Key Concepts Demonstrated
- **LangChain4j `@AiService`**
- **Tool calling / Function calling**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```

## Streaming Chat Endpoint (SSE)

This demo also includes an example of streaming Large Language Model responses using Server-Sent Events (SSE). 
You can interact with the `/api/chat/stream` endpoint to see responses token-by-token:

```bash
curl -N "http://localhost:8080/api/chat/stream?message=Tell%20me%20a%20short%20story"
```
*Note: The `-N` flag is important as it disables curl's default buffering so you can see the text stream in real-time.*
