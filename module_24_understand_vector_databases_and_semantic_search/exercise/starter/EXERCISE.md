# Module 24 - Semantic Search (Vector Stores) - Exercise Instructions

## Exercise Overview

Keyword search isn't cutting it for your FAQ system. You need to implement Semantic Search by converting documents into vectors and querying a `VectorStore` based on conceptual similarity.

---

## Prerequisites
- **Java 25**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Step 1
Load your documents into the `VectorStore` during initialization.

### Step 2
In your search endpoint, construct a `SearchRequest`.

### Step 3
Use `vectorStore.similaritySearch(request)` to find the top matching documents.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] Searching for 'returns' finds documents about 'refunds' (semantic match).
- [ ] The search returns the configured `topK` number of results.
