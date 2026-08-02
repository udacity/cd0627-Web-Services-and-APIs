# Module 23 — LangChain4j Agent — Exercise Instructions

## Exercise Overview

You are building a conversational AI travel assistant using LangChain4j. The agent will have a defined persona, memory for multi-turn conversations, and tool-calling capabilities to look up flights and weather.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**
- **OpenAI API key** — Set the environment variable before running:
  ```bash
  export LANGCHAIN4J_OPEN_AI_CHAT_MODEL_API_KEY=<your-api-key>
  ```

---

## Step-by-Step Implementation Guide

### AI Service Interface (`TravelAssistant.java`)

1. Import `AiService` and `SystemMessage` from LangChain4j (Step 1).
2. Annotate the interface with `@AiService` (Step 2).
3. Add a `@SystemMessage` annotation defining the travel assistant persona (Step 3).
4. Add `@MemoryId String chatId` and `@UserMessage` before the message parameter to enable per-user memory (Step 5).

### Memory Configuration (`ExerciseApplication.java`)

5. Add a `@Bean` method that returns a `ChatMemoryProvider` — use `MessageWindowChatMemory` with a configurable window size (Step 4).

### Tools (`Tools.java`)

6. Annotate the `searchFlights` method with `@Tool` and provide a description (Step 6).
7. Annotate the `getWeather` method with `@Tool` and provide a description (Step 7).

### Controller (`AgentController.java`)

8. Inject the `TravelAssistant` (Step 6).
9. Call `travelAssistant.chat("demo-user", message)` to send user messages to the agent (Step 7).

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

```bash
LANGCHAIN4J_OPEN_AI_CHAT_MODEL_API_KEY=<your-key> mvn spring-boot:run
```

Test with:
```bash
curl -X POST http://localhost:8080/chat \
  -H "Content-Type: text/plain" \
  -d "Find me flights from NYC to Paris"
```

---

## Success Criteria

- [ ] The agent responds with a travel-themed persona.
- [ ] Multi-turn conversations maintain context (memory works).
- [ ] The agent can call tools to look up flights and weather.
- [ ] Tool results are incorporated into the agent's response.
