# Demo Walkthrough: Spring AI — LLM-Powered Features (Module 20)

**Focus:** From Raw HTTP Calls to Spring AI's ChatClient — Simple Chat, Templates, and Tool Calling
**Target Length:** 5 - 7 minutes
**Files:** `ChatController.java`

---

## 0:00 – 1:00 | Introduction & Scenario

*(Screen showing `ChatController.java` open in the IDE)*

"Welcome back. In this demo, we are going to look at Spring AI — the framework for integrating Large Language Models into Spring Boot applications.

"Our scenario is an e-commerce platform that wants to add AI-powered features — intelligent chat, product analysis, and tool-augmented responses. Instead of making raw HTTP calls to the OpenAI API, Spring AI provides a clean abstraction with `ChatClient` that feels like a natural Spring component.

"We are going to cover three capabilities: simple chat, template-based prompts, and tool calling."

## 1:00 – 2:30 | Step 1: Simple Chat

*(Highlight lines 17-24: `simpleChat()` method)*

"Our first endpoint is `GET /api/chat/simple`. The `ChatClient` is injected via constructor — Spring AI auto-configures it from properties.

"The API is fluent: `.prompt()` starts a new prompt builder. `.system()` sets the system prompt — the AI's personality or instructions. `.user()` sets the user's message. `.call()` sends the request. And `.content()` extracts just the text response.

"Notice how clean this is compared to building raw HTTP requests with JSON bodies. Spring AI handles the connection, serialization, and error handling."

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s http://localhost:8080/api/chat/simple`)*

"Let's test it. The AI responds with a greeting. The entire interaction — prompt construction, API call, response parsing — is handled by `ChatClient`."

## 2:30 – 3:30 | Step 2: Template-Based Prompts

*(Highlight lines 26-33: `templateChat()` method)*

"Step 2 is template-based prompting. The `GET /api/chat/template` endpoint accepts an `input` parameter and injects it into the system prompt using `.param("data", input)`.

"This is powerful for building reusable prompt templates. You define the template once with placeholder variables, and swap in dynamic content at runtime.

*(🖥️ Terminal: `curl -s "http://localhost:8080/api/chat/template?input=Spring%20Boot%20vs%20Django"`)*

"The AI receives the full prompt with our input injected, and returns a detailed analysis."

## 3:30 – 5:00 | Step 3: Tool Calling

*(Highlight lines 35-42: `toolChat()` method)*

"Step 3 is tool calling — the most advanced capability. The `GET /api/chat/tool` endpoint adds `.tools("getCurrentDate")` to the prompt.

"Here is what happens behind the scenes: the AI receives the user's message along with a list of available tools. If the AI decides it needs to call a tool to answer the question, it responds with a tool call request instead of a text response. Spring AI intercepts this, executes the tool, sends the result back to the AI, and the AI generates its final response.

"The AI is not just generating text — it is autonomously deciding when to call external functions. This is the foundation of AI agents, which combine LLM reasoning with real-world tool execution.

*(🖥️ Terminal: `curl -s http://localhost:8080/api/chat/tool`)*

"The AI correctly calls the `getCurrentDate` tool and returns today's date."

## 5:00 – 5:30 | Outro & Summary

"To summarize: Spring AI's `ChatClient` provides a fluent API for simple chat, template-based prompts, and tool calling. It abstracts away the HTTP communication with the LLM provider and integrates naturally with Spring's dependency injection.

"Thanks for watching, and I'll see you in the next module."
