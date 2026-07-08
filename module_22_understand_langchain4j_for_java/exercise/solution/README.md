# Module 22 - LangChain4j for Java - Solution

## Solution Walkthrough

The solution implements a LangChain4j AI Service. The framework seamlessly intercepts the LLM's request to run a tool, executes the local Java method, and returns the context to the LLM.

### `TravelAssistant.java` — The Implementation

```java
@AiService
public interface TravelAssistant {
    @SystemMessage("You are an expert travel assistant. You have access to tools to check flights and weather.")
    String chat(String userMessage);
}
```

### Step-by-step Design Decisions:

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | Import AiService and SystemMessage from langchain4j | `src/main/java/com/ecommerce/agent/TravelAssistant.java` |
| 2 | Annotate this interface with `@AiService` | `src/main/java/com/ecommerce/agent/TravelAssistant.java` |
| 3 | Add `@SystemMessage` defining the persona | `src/main/java/com/ecommerce/agent/TravelAssistant.java` |
| 4 | Inject TravelAssistant | `src/main/java/com/ecommerce/agent/AgentController.java` |
| 5 | Call travelAssistant.chat(message) | `src/main/java/com/ecommerce/agent/AgentController.java` |
| 6 | Annotate with `@Tool` and provide a description | `src/main/java/com/ecommerce/agent/Tools.java` |
| 7 | Annotate with `@Tool` and provide a description | `src/main/java/com/ecommerce/agent/Tools.java` |


### Key Concepts Demonstrated
- **LangChain4j `@AiService`**
- **Tool calling / Function calling**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
