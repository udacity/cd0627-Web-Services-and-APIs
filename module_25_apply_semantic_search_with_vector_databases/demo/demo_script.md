# Demo Walkthrough: Semantic Search with Vector Databases (Module 25)

**Focus:** From Keyword Matching to Meaning-Based Search with Embeddings
**Target Length:** 5 - 7 minutes
**Files:** `FaqController.java`, `FaqIngestor.java`

---

## 0:00 – 1:00 | Introduction & The Problem

*(Screen showing the project open in the IDE)*

"Welcome back. In this demo, we are going to look at Semantic Search using vector databases.

"The problem with traditional keyword search is obvious: if a customer searches 'forgot my credentials', a keyword search for 'forgot credentials' will miss an FAQ entry titled 'How to Reset Your Password.' The words are different, but the meaning is the same.

"Semantic search solves this by converting text into mathematical vectors — called embeddings — that capture the meaning of the words. Similar meanings produce similar vectors, regardless of the exact words used."

## 1:00 – 2:30 | Ingesting Documents into the Vector Store

*(Switch tabs to `FaqIngestor.java`)*

"Before we can search, we need to ingest our FAQ data into the vector store. The `FaqIngestor` runs on application startup.

"We load the FAQ entries, split them into chunks using a text splitter, and then call `vectorStore.add(chunks)`. Behind the scenes, Spring AI sends each chunk to the embedding model — which converts the text into a vector of numbers — and stores both the text and its vector in the vector store.

"This is a one-time ingestion step. Once the data is embedded, we can search it by meaning."

## 2:30 – 4:00 | The Search Endpoint

*(Switch tabs to `FaqController.java`)*

"The search endpoint accepts a natural language query. When we call `vectorStore.similaritySearch()`, Spring AI embeds the query into a vector, then finds the stored vectors that are closest in meaning.

"We configure a `SearchRequest` with `topK` — how many results to return — and `similarityThreshold` — the minimum similarity score to include.

"This is fundamentally different from keyword search. The query 'forgot credentials' will match 'How to Reset Your Password' because their embedding vectors are close in the vector space, even though they share zero keywords."

## 4:00 – 5:00 | Testing Semantic Search

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s "http://localhost:8080/api/faq?q=forgot+credentials" | jq`)*

"Let's test it. We search for 'forgot credentials'. The response includes the FAQ entry about password resets — even though the words 'forgot' and 'credentials' do not appear in the answer. Semantic search matched by meaning, not by keywords.

*(🖥️ Terminal: `curl -s "http://localhost:8080/api/faq?q=shipping+cost" | jq`)*

"Another search: 'shipping cost' — and we get the FAQ entry about delivery fees. The vectors for 'shipping cost' and 'delivery fee' are close in the embedding space."

## 5:00 – 5:30 | Outro & Summary

"To summarize: Vector databases store text as mathematical embeddings that capture meaning. Similarity search finds the closest matches by comparing vectors, not keywords. Spring AI integrates this seamlessly with `VectorStore.similaritySearch()`. This is the foundation for RAG — Retrieval-Augmented Generation — where your AI answers questions grounded in your own data instead of relying solely on its training data.

"Thanks for watching, and I'll see you in the next module."
