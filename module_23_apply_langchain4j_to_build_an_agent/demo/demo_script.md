# Demo Walkthrough: LangChain4j — AI Agents (Module 23)

**Focus:** From Static APIs to an AI Agent That Calls Tools Autonomously
**Target Length:** 5 - 7 minutes
**Files:** `TravelAssistant.java`, `Tools.java`, `AgentController.java`, `SseChatController.java`

---

## 0:00 – 1:00 | Introduction & Scenario

*(Screen showing `TravelAssistant.java` open in the IDE)*

"Welcome back. In this demo, we are going to look at LangChain4j — a framework for building AI agents in Java.

"The difference between a chat endpoint and an agent is that an agent can take actions. A basic LLM endpoint analyzes text and returns a response. An agent goes further — it can call tools like checking flight availability, looking up weather — and compose the results into a final answer. The AI decides which tools to call and in what order."

## 1:00 – 2:30 | The AI Service Interface

*(Highlight `TravelAssistant.java`)*

"LangChain4j's `@AiService` annotation is similar to Spring's declarative HTTP interfaces. We declare a Java interface — `TravelAssistant` — with a `chat` method. The `@SystemMessage` sets the AI's role: 'You are an expert travel assistant with access to flight and weather tools.'

"LangChain4j generates the implementation at runtime. When `chat()` is called, it sends the message to the LLM along with descriptions of available tools. The LLM decides whether to call a tool or respond directly."

## 2:30 – 3:30 | Tool Definitions

*(Switch tabs to `Tools.java`)*

"Here are the tools the agent can call. Each method is annotated with `@Tool` and a natural language description. `checkFlight` takes source and destination, `getWeather` takes a city.

"The LLM reads these descriptions to understand when to use each tool. If a user asks 'What is the weather in Paris?', the LLM sees the `getWeather` tool description, decides to call it, and Spring injects the response back into the conversation."

## 3:30 – 5:00 | The Agent in Action

*(Switch tabs to `AgentController.java`)*

"The controller is trivially simple. It injects `TravelAssistant` and exposes `GET /ask`. Let's see the agent work.

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s "http://localhost:8080/ask?message=Is+there+a+flight+from+NYC+to+London+and+what+is+the+weather+there?"`)*

"We ask: 'Is there a flight from NYC to London and what is the weather there?' The agent makes two autonomous decisions: first, call `checkFlight("NYC", "London")`, then call `getWeather("London")`. It composes both results into a natural language response.

"The agent is not following a script — it is reasoning about which tools to call based on the user's question."

## 5:00 – 6:00 | Streaming Responses

*(Switch tabs to `SseChatController.java`)*

"LangChain4j also supports streaming. `SseChatController` uses a `StreamingChatLanguageModel` to stream tokens to the client as Server-Sent Events. Instead of waiting for the full response, the user sees words appear in real time — the same experience as ChatGPT.

"The `StreamingResponseHandler` has three callbacks: `onNext` for each token, `onComplete` when done, and `onError` for failures."

## 6:00 – 6:30 | Outro & Summary

"To summarize: LangChain4j's `@AiService` turns a Java interface into an AI agent. `@Tool` methods give the agent real capabilities. And streaming support provides a real-time, ChatGPT-like experience.

"Thanks for watching, and I'll see you in the next module."
