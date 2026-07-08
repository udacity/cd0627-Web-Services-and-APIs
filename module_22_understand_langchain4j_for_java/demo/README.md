# Module 22 - Spring AI - Output Converters

## Demo Walkthrough

This demo dives deeper into Spring AI by exploring Structured Output Converters. We show how to instruct the LLM to format its response as a strictly defined JSON structure.

### `ChatController.java` — Core Implementation

```java
@GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return chatClient.prompt().user(message).call().content();
    }
```

### Execution Workflow

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `BeanOutputConverter` | Create a `BeanOutputConverter` for your target Record class. |
| 2 | Step 2 | Append the converter's format instructions to your prompt. |
| 3 | Step 3 | Use the converter to parse the AI's string response into a Java object. |


### Expected Output

```
Application started successfully.
Expected API behaviors active.
```

### Key Concepts Demonstrated
- **`BeanOutputConverter`**
- **Prompt Templates for format instructions**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
