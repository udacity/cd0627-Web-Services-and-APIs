# Module 24 - Semantic Search (Vector Stores) - Solution

## Solution Walkthrough

The solution implements semantic search utilizing Spring AI's `SimpleVectorStore`. The search endpoint embeds the user's query and returns the conceptually closest matching documents.

### `FaqController.java` — The Implementation

```java
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

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `VectorStore` | Load your documents into the `VectorStore` during initialization. |
| 2 | `SearchRequest` | In your search endpoint, construct a `SearchRequest`. |
| 3 | `vectorStore.similaritySearch(request)` | Use `vectorStore.similaritySearch(request)` to find the top matching documents. |


### Expected Output

```
══════════════════════════════════════════
 Integration Successful 
══════════════════════════════════════════
```

### Key Concepts Demonstrated
- **Embeddings and Vectorization**
- **Cosine Similarity Search**
- **Spring AI `SimpleVectorStore`**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
