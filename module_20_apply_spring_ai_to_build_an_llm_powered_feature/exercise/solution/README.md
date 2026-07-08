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

| Step | Task |
|------|-----------|
| 1 | In `src/main/java/com/ecommerce/ai/ReviewController.java`, inject `ChatClient.Builder` into your controller. |
| 2 | Call `chatClient.prompt().user(message).call().content()` to get the AI's response. |


### Key Concepts Demonstrated
- **Spring AI `ChatClient`**
- **System vs User Prompts**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
