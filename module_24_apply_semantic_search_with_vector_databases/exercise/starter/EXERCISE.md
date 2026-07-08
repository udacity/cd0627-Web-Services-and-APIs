# Module 24 - Vector Databases and Semantic Search - Exercise Instructions

## Exercise Overview

Keyword search isn't cutting it for your FAQ system. You need to implement Semantic Search by converting documents into vectors and querying a `VectorStore` based on conceptual similarity.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task |
|------|-----------|
| 1 | In `src/main/java/com/ecommerce/search/FaqIngestor.java`, load and parse documents into the `VectorStore`. |
| 2 | In `src/main/java/com/ecommerce/search/FaqController.java`, use `vectorStore.similaritySearch(request)` to find the top matching documents. |


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
