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
