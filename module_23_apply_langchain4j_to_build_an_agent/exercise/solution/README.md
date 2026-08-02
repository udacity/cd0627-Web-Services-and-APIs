# Module 23 - LangChain4j for Java - Solution

## Solution Walkthrough

The solution implements a LangChain4j AI Service. The framework seamlessly intercepts the LLM's request to run a tool, executes the local Java method, and returns the context to the LLM.

### `TravelAssistant.java` — The Implementation

```java
@AiService
public interface TravelAssistant {
    @SystemMessage("You are an expert travel assistant. You have access to tools to check flights and weather.")
    String chat(@MemoryId String chatId, @UserMessage String userMessage);
}
```

### Step-by-step Design Decisions:

1. In `src/main/java/com/ecommerce/agent/TravelAssistant.java`, annotate the interface with `@AiService` and define the persona using `@SystemMessage`. Add `@MemoryId` and `@UserMessage` annotations to the `chat` method parameters.
2. In `src/main/java/com/ecommerce/agent/ExerciseApplication.java`, configure a `ChatMemoryProvider` bean to enable persistent conversation memory.
3. In `src/main/java/com/ecommerce/agent/Tools.java`, annotate the flight and weather methods with `@Tool` so the LLM can invoke them.


### Key Concepts Demonstrated
- **LangChain4j `@AiService`**
- **Persistent Conversation Memory (`ChatMemoryProvider`, `@MemoryId`)**
- **Tool calling / Function calling**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
