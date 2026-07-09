# Module 24 - Vector Databases and Semantic Search - Solution

## Solution Walkthrough

The solution implements semantic search utilizing Spring AI's `SimpleVectorStore`.

### `FaqController.java` — The Implementation

```java
@RestController
public class FaqController {

    private final VectorStore vectorStore;

    public FaqController(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @GetMapping("/search")
    public List<String> searchFaq(
            @RequestParam String query,
            @RequestParam(required = false) String category) {
        
        SearchRequest.Builder request = SearchRequest.builder().query(query).topK(3);
        
        if (category != null && !category.isBlank()) {
            request = request.filterExpression("category == '" + category + "'");
        }
        
        return vectorStore.similaritySearch(request.build()).stream()
                .map(Document::getText)
                .collect(Collectors.toList());
    }
```

### Step-by-step Design Decisions:

1. In `src/main/java/com/ecommerce/search/FaqIngestor.java`, load and parse documents into the `VectorStore`.
2. In `src/main/java/com/ecommerce/search/FaqController.java`, use `vectorStore.similaritySearch(request)` to find the top matching documents.


### Key Concepts Demonstrated
- **Embeddings and Vectorization**
- **Cosine Similarity Search**
- **Spring AI `SimpleVectorStore`**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
