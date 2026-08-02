# Solution Walkthrough: LangChain4j Agent (Module 23)

**Focus:** Building the Travel Assistant with @AiService, @Tool, and @MemoryId
**Target Length:** 5 - 7 minutes
**Files:** `TravelAssistant.java`, `Tools.java`, `AgentController.java`

---

## 0:00 – 1:00 | Introduction & The Exercise

*(Screen showing the project structure)*

"Welcome back. In this video, we're going to walk through the solution to the LangChain4j Agent exercise.

"Our goal was to build a Travel Assistant agent that can check flights, look up weather, and maintain conversation memory. The exercise had nine steps — from defining tools to wiring up the @AiService.

"Let's walk through the key components."

## 1:00 – 2:30 | The Tool Definitions

*(Switch tabs to `Tools.java`)*

"We start with `Tools.java`. Each method is annotated with `@Tool` and a natural language description — this description is what the LLM reads to decide when to call the tool.

"`checkFlight` takes a `source` and `destination` and returns flight availability. `getWeather` takes a `city` and returns the weather. These are stubbed for the exercise, but in production you would call real APIs here.

"The class is annotated with `@Component`, which makes it a Spring bean. LangChain4j automatically discovers all `@Tool`-annotated methods in Spring beans and makes them available to the agent."

## 2:30 – 3:30 | The @AiService Interface

*(Switch tabs to `TravelAssistant.java`)*

"The core of the agent is `TravelAssistant`. It is a Java interface annotated with `@AiService`. The `@SystemMessage` defines the AI's personality and capabilities.

"The `chat` method has two parameters. `@UserMessage String userMessage` is the user's input. And `@MemoryId String chatId` is the conversation memory identifier — this allows the agent to remember previous messages within the same conversation. Each unique `chatId` gets its own memory context."

## 3:30 – 4:30 | The Controller

*(Switch tabs to `AgentController.java`)*

"The controller is simple. We inject `TravelAssistant` and expose `GET /ask`. When a request comes in, we call `travelAssistant.chat("demo-user", message)`.

"The `"demo-user"` string is the memory ID. All requests with the same ID share conversation context — so the agent remembers what was asked before."

## 4:30 – 5:30 | Running & Verification

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s "http://localhost:8080/ask?message=Check+flights+from+Tokyo+to+Paris"`)*

"Let's test the agent. First: 'Check flights from Tokyo to Paris.' The agent calls the `checkFlight` tool and reports the availability.

*(🖥️ Terminal: `curl -s "http://localhost:8080/ask?message=What+about+the+weather+there?"`)*

"Now: 'What about the weather there?' Notice we said 'there' — not 'Paris.' The agent uses its conversation memory to understand that 'there' refers to Paris from the previous message, calls `getWeather("Paris")`, and returns the result.

"This is the power of the `@MemoryId` annotation — the agent maintains context across multiple turns of conversation."

## 5:30 – 6:00 | Outro

"To summarize: We built an AI agent with three components — `@Tool` methods for capabilities, an `@AiService` interface for the agent definition, and `@MemoryId` for conversation memory. The LLM autonomously decides which tools to call, and the agent maintains context across conversation turns.

"Great job if you got this working. I'll see you in the next module."
