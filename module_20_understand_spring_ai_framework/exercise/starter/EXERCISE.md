# Module 20 - Spring AI Framework - Exercise Instructions

## Exercise Overview

Your product manager wants to add AI capabilities to the app. You need to integrate the OpenAI API using Spring AI's `ChatClient` to generate simple text responses.

---

## Prerequisites
- **Java 21+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | Build the ChatClient | `src/main/java/com/ecommerce/ai/ReviewController.java` |
| 2 | Use the ChatClient fluent API to extract structured data into a ReviewSummary | `src/main/java/com/ecommerce/ai/ReviewController.java` |
| 3 | Handle parsing exceptions by returning a fallback ReviewSummary | `src/main/java/com/ecommerce/ai/ReviewController.java` |


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

- [ ] The endpoint successfully calls the LLM and returns a text response.
