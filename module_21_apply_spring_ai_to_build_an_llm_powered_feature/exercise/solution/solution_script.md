# Solution Walkthrough: Spring AI — LLM-Powered Feature (Module 21)

**Focus:** Structured Output — The Review Analyzer
**Target Length:** 5 - 7 minutes
**File:** `ReviewController.java`

---

## 0:00 – 1:00 | Introduction & The Exercise

*(Screen showing `ReviewController.java` open in the IDE)*

"Welcome back. In this video, we're going to walk through the solution to the Spring AI exercise.

"Our goal was to build a review analysis endpoint. When a customer submits a raw product review, the LLM analyzes it and returns structured data — a summary, key points, and a sentiment — as a Java object, not raw text.

"Let's walk through the implementation."

## 1:00 – 2:30 | The ChatClient Setup

*(Highlight lines 14-19: constructor and ChatClient field)*

"First, the setup. We inject `ChatClient.Builder` through the constructor and call `.build()` to create our `ChatClient` instance. Spring AI auto-configures the builder with the OpenAI connection details from `application.properties`.

"Notice we store the built client as a field. This is the recommended pattern — build once, reuse across requests."

## 2:30 – 4:00 | Structured Output with .entity()

*(Highlight lines 21-35: `analyzeReview()` method)*

"The endpoint is `POST /api/reviews/analyze`. It accepts a raw review string as the request body.

"The key line is `.entity(ReviewSummary.class)`. Instead of calling `.content()` to get raw text, we call `.entity()` and pass a Java class. Spring AI instructs the LLM to return its response in a JSON format that matches our `ReviewSummary` record — which has fields for `summary`, `keyPoints` (a list of strings), and `sentiment`.

"Spring AI handles the conversion automatically — the LLM's text output is parsed into a strongly-typed Java object. No manual JSON parsing, no regex extraction.

"We also wrap the call in a try-catch. If the LLM returns malformed output or the API call fails, we return a fallback `ReviewSummary` with 'Analysis Failed' instead of crashing."

## 4:00 – 5:30 | Running & Verification

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s -X POST http://localhost:8080/api/reviews/analyze -H "Content-Type: text/plain" -d "This laptop has amazing battery life and the screen is gorgeous. However, the keyboard feels a bit mushy and the trackpad is too small. Overall a solid purchase for the price." | jq`)*

"Let's test it. We send a product review as plain text. The response is a structured `ReviewSummary` object.

"Looking at the JSON, we see: `summary` — a one-line summary of the review. `keyPoints` — an array with specific points like 'amazing battery life', 'gorgeous screen', 'mushy keyboard'. And `sentiment` — 'POSITIVE' or 'MIXED'.

"This is the power of structured output. The LLM understood the nuances of a natural language review and extracted structured, machine-readable data that we can store in a database, aggregate across products, or feed into dashboards."

## 5:30 – 6:00 | Outro

"To summarize: Spring AI's `.entity()` method bridges the gap between LLM text output and Java objects. We built a production-ready review analyzer with structured output, error handling, and zero manual parsing.

"Great job if you got this working. I'll see you in the next module."
