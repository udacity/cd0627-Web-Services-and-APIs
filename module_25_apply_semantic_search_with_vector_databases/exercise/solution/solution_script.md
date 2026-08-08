# Solution Walkthrough: Semantic Search with Vector Databases (Module 25)

**Focus:** Ingesting FAQs and Building a Semantic Search Endpoint
**Target Length:** 5 - 7 minutes
**Files:** `FaqIngestor.java`, `FaqController.java`

---

## Prerequisites

Before running, export your OpenAI API key:

```bash
export OPENAI_API_KEY=<your-openai-api-key>
```

---

## 0:00 – 1:00 | Introduction & The Exercise

*(Screen showing the project open in the IDE)*

"Welcome back. In this video, we're going to walk through the solution to the Semantic Search exercise.

"Our goal was to build an FAQ search endpoint that uses embeddings instead of keywords. We needed to ingest FAQ data into a vector store on startup and then expose a search endpoint that finds the most relevant FAQ entries by meaning.

"Let's walk through each step."

## 1:00 – 2:30 | The Ingestor

*(Switch tabs to `FaqIngestor.java`)*

"The `FaqIngestor` runs on application startup using `@EventListener(ApplicationReadyEvent.class)`. It reads the FAQ resource file, splits it by blank lines — each FAQ is a question-answer pair — and creates a `Document` for each entry.

"For each document, we detect the category from the text content: entries about passwords or laptops get tagged 'IT', vacation or payroll entries get tagged 'HR'. Then all documents are added to the vector store, where each one is embedded into a vector for similarity search."

## 2:30 – 4:00 | The Search Controller

*(Switch tabs to `FaqController.java`)*

"The controller exposes `GET /search` with a required `query` parameter and an optional `category` parameter. When a request comes in, we call `vectorStore.similaritySearch()`.

"The `SearchRequest` is configured with `topK(2)` — return the 2 most relevant results. If a `category` parameter is provided, we add a `filterExpression` to narrow results to that category.

"Behind the scenes, the query text is embedded into a vector, and the vector store finds the stored vectors closest to it using cosine similarity. The results are returned as `Document` objects, and we extract just the text content."

## 4:00 – 5:00 | Running & Verification

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s "http://localhost:8080/search?query=forgot+credentials" | jq`)*

"Let's test it. We search for 'forgot credentials'. The top result is the FAQ about resetting your password — matched by meaning, not exact keywords. The query says 'forgot credentials' but the FAQ says 'reset my password'. Embeddings capture that semantic relationship.

*(🖥️ Terminal: `curl -s "http://localhost:8080/search?query=time+off+request" | jq`)*

"Searching for 'time off request' returns the vacation policy FAQ — even though the original text says 'paid time off' and 'approved by your manager' instead of 'time off request'. The embeddings understand they mean the same thing."

## 5:00 – 5:30 | Outro

"To summarize: We ingested FAQ documents into a vector store at startup, and built a search endpoint that finds relevant answers by meaning using embedding similarity. This semantic search capability is the building block for RAG systems, which combine search results with LLM reasoning.

"Great job if you got this working. I'll see you in the next module."
