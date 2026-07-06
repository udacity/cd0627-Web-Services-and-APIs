# Module 26 - RAG (Retrieval-Augmented Generation) - Exercise Instructions

## Exercise Overview

You want the AI to answer customer support questions, but it hallucinates answers about policies it doesn't know. You must implement the RAG pattern using `QuestionAnswerAdvisor` to ground the AI in your specific documents.

---

## Prerequisites
- **Java 25**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Step 1
Configure the `ChatClient` with a `QuestionAnswerAdvisor`.

### Step 2
Pass the `VectorStore` and a `SearchRequest` to the advisor.

### Step 3
Define a strict system prompt instructing the AI to only use the provided context.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] The AI accurately answers questions based on your FAQ documents.
- [ ] The AI responds 'I don't know' for out-of-scope questions.
