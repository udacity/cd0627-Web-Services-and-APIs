# Module 20 - Spring AI - ChatClient - Solution

## Solution Walkthrough

The solution seamlessly integrates generative AI. The controller abstracts away the complex REST calls to the OpenAI API, relying on `ChatClient` to handle the prompt execution and return the raw string response.

### `ReviewController.java` — The Implementation

```java
@PostMapping("/api/reviews/analyze")
    public ReviewSummary analyzeReview(@RequestBody String rawReview) {
        try {
            return chatClient.prompt()
                    .system("Analyze the following product review and extract the key points.")
                    .user(rawReview)
                    .call()
                    .entity(ReviewSummary.class);
        } catch (Exception e) {
            log.error("Failed to parse LLM output into ReviewSummary", e);
            return new ReviewSummary("Analysis Failed", List.of("Could not parse review"), "UNKNOWN");
        }
    }
```

### Step-by-step Design Decisions:

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `ChatClient.Builder` | Inject `ChatClient.Builder` into your controller. |
| 2 | `ChatClient` | Build the `ChatClient` with a default system prompt. |
| 3 | `chatClient.prompt().user(message).call().content()` | Call `chatClient.prompt().user(message).call().content()` to get the AI's response. |


### Expected Output

```
══════════════════════════════════════════
 Integration Successful 
══════════════════════════════════════════
```

### Key Concepts Demonstrated
- **Spring AI `ChatClient`**
- **System vs User Prompts**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
