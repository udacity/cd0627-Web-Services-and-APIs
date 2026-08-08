# Demo Walkthrough: Semantic Search with Vector Databases (Module 25)

**Focus:** From Keyword Matching to Meaning-Based Search with Embeddings
**Target Length:** 5 - 7 minutes
**Files:** `SemanticDemoRunner.java`

---

## Prerequisites

Before running, export your OpenAI API key:

```bash
export OPENAI_API_KEY=<your-openai-api-key>
```

---

## 0:00 – 1:00 | Introduction & The Problem

*(Screen showing the project open in the IDE)*

"Welcome back. In this demo, we are going to look at Semantic Search using vector databases.

"The problem with traditional keyword search is obvious: if a customer searches 'forgot my credentials', a keyword search for 'forgot credentials' will miss an FAQ entry titled 'How to Reset Your Password.' The words are different, but the meaning is the same.

"Semantic search solves this by converting text into mathematical vectors — called embeddings — that capture the meaning of the words. Similar meanings produce similar vectors, regardless of the exact words used."

## 1:00 – 2:30 | Step 1: Raw Embeddings

*(Switch tabs to `SemanticDemoRunner.java`, highlight the `run()` method)*

"Our demo is a `CommandLineRunner` that walks through three steps. First, raw embeddings. We call `embeddingModel.embed("Hello World")`, which converts the string into a high-dimensional vector — an array of floating-point numbers.

"Each number represents a dimension of meaning. The vector for 'cat' and the vector for 'kitten' will be close together in this space, even though the words are completely different. That is what makes semantic search possible."

## 2:30 – 3:30 | Step 2: Populating the Vector Store

*(Highlight the document creation and `vectorStore.add()` call)*

"In Step 2, we create three documents: 'The cat sat on the mat', 'A dog barked loudly', and 'Felines prefer to rest on rugs.' We add them to a `SimpleVectorStore`.

"Behind the scenes, each document is embedded into a vector and stored alongside the original text. The vector store now has both the human-readable text and its mathematical representation."

## 3:30 – 5:00 | Step 3: Semantic Search in Action

*(Highlight the `similaritySearch` call)*

"Step 3 is where the magic happens. We search for 'Where do kittens sleep?' — a query that shares zero keywords with any of our documents. Traditional search would return nothing.

"We call `vectorStore.similaritySearch()` with `topK(1)` to get the single best match.

*(🖥️ Terminal: `mvn spring-boot:run`)*

"Let's run it. Looking at the console output:

"Step 1 shows the raw embedding vector — over 1500 dimensions. Step 2 confirms the documents were added. And Step 3 — the top match for 'Where do kittens sleep?' is 'Felines prefer to rest on rugs.' No keyword overlap at all — the embeddings captured the semantic relationship between kittens/felines and sleep/rest."

## 5:00 – 5:30 | Outro & Summary

"To summarize: Vector databases store text as mathematical embeddings that capture meaning. Similarity search finds the closest matches by comparing vectors, not keywords. Spring AI integrates this seamlessly with `VectorStore.similaritySearch()`. This is the foundation for RAG — Retrieval-Augmented Generation — where your AI answers questions grounded in your own data instead of relying solely on its training data.

"Thanks for watching, and I'll see you in the next module."
