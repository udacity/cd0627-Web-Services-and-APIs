# Module 20 - Spring AI Framework - Solution

## Solution Walkthrough

The solution seamlessly integrates generative AI. The controller abstracts away REST calls to the OpenAI API, relying on `ChatClient`.

### `ReviewController.java` — The Implementation

```java
@RestController
public class ReviewController {

    private static final Logger log = LoggerFactory.getLogger(ReviewController.class);
    private final ChatClient chatClient;

    public ReviewController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

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

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | Build the ChatClient | `src/main/java/com/ecommerce/ai/ReviewController.java` |
| 2 | Use the ChatClient fluent API to extract structured data into a ReviewSummary | `src/main/java/com/ecommerce/ai/ReviewController.java` |
| 3 | Handle parsing exceptions by returning a fallback ReviewSummary | `src/main/java/com/ecommerce/ai/ReviewController.java` |


### Key Concepts Demonstrated
- **Spring AI `ChatClient`**
- **System vs User Prompts**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
