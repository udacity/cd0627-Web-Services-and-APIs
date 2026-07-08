# Module 20 - Spring AI - ChatClient - Exercise Instructions

## Exercise Overview

Your product manager wants to add AI capabilities to the app. You need to integrate the OpenAI API using Spring AI's `ChatClient` to generate simple text responses.

---

## Prerequisites
- **Java 25**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Step 1
Inject `ChatClient.Builder` into your controller.

### Step 2
Build the `ChatClient` with a default system prompt.

### Step 3
Call `chatClient.prompt().user(message).call().content()` to get the AI's response.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] The endpoint successfully calls the LLM and returns a text response.
- [ ] The AI obeys the default system prompt.
