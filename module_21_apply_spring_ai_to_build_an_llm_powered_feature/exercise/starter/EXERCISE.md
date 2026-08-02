# Module 21 — Spring AI Framework — Exercise Instructions

## Exercise Overview

Your product manager wants AI-powered product review analysis. You will integrate the OpenAI API using Spring AI's `ChatClient` to extract **structured data** from raw review text — producing a typed `ReviewSummary` record with a title, bullet points, and sentiment.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**
- **OpenAI API key** — Set the environment variable before running:
  ```bash
  export OPENAI_API_KEY=<your-api-key>
  ```
  The default base URL is `https://openai.vocareum.com`. Override with:
  ```bash
  export SPRING_AI_OPENAI_BASE_URL=https://api.openai.com
  ```

---

## Step-by-Step Implementation Guide

1. In `src/main/java/com/ecommerce/ai/ReviewController.java`, **build the `ChatClient`** from the injected `ChatClient.Builder` in the constructor (Step 1).
2. In the `analyzeReview` method, use the **ChatClient fluent API** to send a prompt and extract structured output into a `ReviewSummary` record using `.call().entity(ReviewSummary.class)` (Step 2). This leverages Spring AI's built-in `BeanOutputConverter` to parse the LLM's JSON response into a typed Java object.
3. Wrap the call in a `try-catch` and return a **fallback `ReviewSummary`** if parsing fails (Step 3).

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

```bash
OPENAI_API_KEY=<your-key> mvn spring-boot:run
```

Test with:
```bash
curl -X POST http://localhost:8080/api/reviews/analyze \
  -H "Content-Type: text/plain" \
  -d "Excellent phone. Camera is amazing but the price is too high."
```

---

## Success Criteria

- [ ] The endpoint returns a structured `ReviewSummary` JSON with `title`, `bulletPoints`, and `sentiment` fields.
- [ ] Parsing failures return a fallback response instead of a 500 error.
