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

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | Create a SearchRequest with the given query. | `src/main/java/com/ecommerce/search/FaqController.java` |
| 2 | Set topK to 3. | `src/main/java/com/ecommerce/search/FaqController.java` |
| 3 | If the category parameter is provided, add a filter expression: "category == '" + category + "'" | `src/main/java/com/ecommerce/search/FaqController.java` |
| 4 | Use TextReader to read 'faqResource'. | `src/main/java/com/ecommerce/search/FaqIngestor.java` |
| 5 | Pass the document through a TokenTextSplitter to create chunks. | `src/main/java/com/ecommerce/search/FaqIngestor.java` |
| 6 | For each chunk, manually inject metadata based on text content: | `src/main/java/com/ecommerce/search/FaqIngestor.java` |
| 7 | Save the chunked documents to the SimpleVectorStore. | `src/main/java/com/ecommerce/search/FaqIngestor.java` |


### Key Concepts Demonstrated
- **Embeddings and Vectorization**
- **Cosine Similarity Search**
- **Spring AI `SimpleVectorStore`**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
