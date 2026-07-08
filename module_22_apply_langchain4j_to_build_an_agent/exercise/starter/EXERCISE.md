# Module 22 - LangChain4j for Java - Exercise Instructions

## Exercise Overview

You need to build an intelligent Travel Assistant agent that has real-world capabilities. You must use LangChain4j to build an AI Service that utilizes `@Tool` methods to check flights and weather.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task |
|------|-----------|
| 1 | In `src/main/java/com/ecommerce/agent/TravelAssistant.java`, annotate the interface with `@AiService` and define the persona using `@SystemMessage`. |
| 2 | In `src/main/java/com/ecommerce/agent/Tools.java`, annotate the flight and weather methods with `@Tool` so the LLM can invoke them. |


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
