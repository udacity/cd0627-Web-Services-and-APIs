# Module 24 - Vector Databases and Semantic Search - Exercise Instructions

## Exercise Overview

Keyword search isn't cutting it for your FAQ system. You need to implement Semantic Search by converting documents into vectors and querying a `VectorStore` based on conceptual similarity.

---

## Prerequisites
- **Java 21+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | Create a SearchRequest with the given query. | `src/main/java/com/ecommerce/search/FaqController.java` |
| 2 | Set topK to 3. | `src/main/java/com/ecommerce/search/FaqController.java` |
| 3 | If the category parameter is provided, add a filter expression: "category == '" + category + "'" | `src/main/java/com/ecommerce/search/FaqController.java` |
| 4 | Use TextReader to read 'faqResource'. | `src/main/java/com/ecommerce/search/FaqIngestor.java` |
| 5 | Pass the document through a TokenTextSplitter to create chunks. | `src/main/java/com/ecommerce/search/FaqIngestor.java` |
| 6 | For each chunk, manually inject metadata based on text content: | `src/main/java/com/ecommerce/search/FaqIngestor.java` |
| 7 | Save the chunked documents to the SimpleVectorStore. | `src/main/java/com/ecommerce/search/FaqIngestor.java` |


> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] Searching for 'returns' finds documents about 'refunds' (semantic match).
