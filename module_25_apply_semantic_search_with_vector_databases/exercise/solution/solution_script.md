# Solution Walkthrough: Semantic Search with Vector Databases (Module 25)

**Focus:** Ingesting FAQs and Building a Semantic Search Endpoint
**Target Length:** 5 - 7 minutes
**Files:** `FaqIngestor.java`, `FaqController.java`

---

## 0:00 – 1:00 | Introduction & The Exercise

*(Screen showing the project open in the IDE)*

"Welcome back. In this video, we're going to walk through the solution to the Semantic Search exercise.

"Our goal was to build an FAQ search endpoint that uses embeddings instead of keywords. We needed to ingest FAQ data into a vector store on startup and then expose a search endpoint that finds the most relevant FAQ entries by meaning.

"Let's walk through each step."

## 1:00 – 2:30 | The Ingestor

*(Switch tabs to `FaqIngestor.java`)*

"The `FaqIngestor` runs on application startup using `@EventListener(ApplicationReadyEvent.class)`. It loads FAQ entries from a text resource file, splits them into chunks using a `TokenTextSplitter`, and adds them to the vector store.

"Each chunk goes through the embedding model, which converts the text into a high-dimensional vector. The vector store — `SimpleVectorStore` in our case — holds both the original text and its embedding. This is our searchable knowledge base."

## 2:30 – 4:00 | The Search Controller

*(Switch tabs to `FaqController.java`)*

"The controller exposes `GET /api/faq` with a query parameter `q`. When a request comes in, we call `vectorStore.similaritySearch()`.

"The `SearchRequest` is configured with `topK(3)` — return the 3 most relevant results — and `similarityThreshold(0.75)` — only include results with at least 75% similarity.

"Behind the scenes, the query text is embedded into a vector, and the vector store finds the stored vectors closest to it using cosine similarity. The results are returned as `Document` objects containing the original text and metadata."

## 4:00 – 5:00 | Running & Verification

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s "http://localhost:8080/api/faq?q=forgot+credentials" | jq`)*

"Let's test it. We search for 'forgot credentials'. The response includes FAQ entries about password resets — matched by meaning, not exact keywords.

*(🖥️ Terminal: `curl -s "http://localhost:8080/api/faq?q=delivery+time" | jq`)*

"Searching for 'delivery time' returns FAQ entries about shipping — even if the original text uses 'estimated arrival' instead of 'delivery time'. The embeddings capture the semantic relationship."

## 5:00 – 5:30 | Outro

"To summarize: We ingested FAQ documents into a vector store at startup, and built a search endpoint that finds relevant answers by meaning using embedding similarity. This semantic search capability is the building block for RAG systems, which combine search results with LLM reasoning.

"Great job if you got this working. I'll see you in the next module."
