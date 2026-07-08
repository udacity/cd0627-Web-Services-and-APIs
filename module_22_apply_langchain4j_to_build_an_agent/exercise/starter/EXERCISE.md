# Module 22 - LangChain4j for Java - Exercise Instructions

## Exercise Overview

You need to build an intelligent Travel Assistant agent that has real-world capabilities. You must use LangChain4j to build an AI Service that utilizes `@Tool` methods to check flights and weather.

---

## Prerequisites
- **Java 21+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | Import AiService and SystemMessage from langchain4j | `src/main/java/com/ecommerce/agent/TravelAssistant.java` |
| 2 | Annotate this interface with `@AiService` | `src/main/java/com/ecommerce/agent/TravelAssistant.java` |
| 3 | Add `@SystemMessage` defining the persona | `src/main/java/com/ecommerce/agent/TravelAssistant.java` |
| 4 | Inject TravelAssistant | `src/main/java/com/ecommerce/agent/AgentController.java` |
| 5 | Call travelAssistant.chat(message) | `src/main/java/com/ecommerce/agent/AgentController.java` |
| 6 | Annotate with `@Tool` and provide a description | `src/main/java/com/ecommerce/agent/Tools.java` |
| 7 | Annotate with `@Tool` and provide a description | `src/main/java/com/ecommerce/agent/Tools.java` |


> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] The AI can dynamically decide to call the tools when asked about flights or weather.
- [ ] The LangChain4j framework automatically routes the tool execution.
