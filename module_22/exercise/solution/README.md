# Module 22 - Spring AI - Output Converters - Solution

## Solution Walkthrough

The solution extracts structured data from the LLM. The converter automatically generates format instructions detailing the required JSON schema, and then parses the response back into a strongly-typed Java Record.

### `AgentController.java` — The Implementation

```java
@GetMapping("/ask")
    public String ask(@RequestParam String message) {
        return chatClient.prompt().user(message).call().content();
    }
```

### Step-by-step Design Decisions:

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `BeanOutputConverter` | Create a `BeanOutputConverter` for your target Record class. |
| 2 | Step 2 | Append the converter's format instructions to your prompt. |
| 3 | Step 3 | Use the converter to parse the AI's string response into a Java object. |


### Expected Output

```
══════════════════════════════════════════
 Integration Successful 
══════════════════════════════════════════
```

### Key Concepts Demonstrated
- **`BeanOutputConverter`**
- **Prompt Templates for format instructions**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
