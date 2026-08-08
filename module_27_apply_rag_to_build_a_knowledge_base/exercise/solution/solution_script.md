# Solution Walkthrough: RAG Knowledge Base (Module 27)

**Focus:** The Support Oracle — Ingesting a Product Manual and Building a Grounded Q&A Endpoint
**Target Length:** 5 - 7 minutes
**Files:** `CorpusIngestor.java`, `SupportOracleController.java`

---

## Prerequisites

Before running, export your OpenAI API key:

```bash
export OPENAI_API_KEY=<your-openai-api-key>
```

---

## 0:00 – 1:00 | Introduction & The Exercise

*(Screen showing the project open in the IDE)*

"Welcome back. In this video, we're going to walk through the solution to the RAG Knowledge Base exercise.

"Our goal was to build a 'Support Oracle' — a Q&A endpoint that answers questions using a product manual as its knowledge base. We needed to ingest the manual into a vector store and build a controller that uses the `QuestionAnswerAdvisor` to ground LLM responses in the actual manual content."

## 1:00 – 2:30 | Step 1: The Corpus Ingestor

*(Switch tabs to `CorpusIngestor.java`)*

"Step 1 is ingesting the product manual. The `CorpusIngestor` uses `@EventListener(ApplicationReadyEvent.class)` to run on startup.

"We load the manual using `TextReader`, which reads a text file from the classpath. We add metadata — `source: product-manual.txt` — so we can trace answers back to their source.

"Then we split the document into chunks using `TokenTextSplitter`. This is critical — LLMs have context limits, so we need to break the manual into digestible pieces. Each chunk is added to the vector store with its embedding.

"After this runs, the entire product manual is searchable by meaning."

## 2:30 – 4:30 | Step 2: The Support Oracle Controller

*(Switch tabs to `SupportOracleController.java`)*

"Step 2 is the Q&A controller. Look at the constructor — we build the `ChatClient` with two key configurations.

"First, `.defaultSystem()` sets a guardrail: 'If the provided context does not contain the answer, reply exactly with I do not have enough information.' This prevents hallucination — the LLM will not make up answers.

"Second, `.defaultAdvisors()` attaches a `QuestionAnswerAdvisor` configured with `topK(2)` and `similarityThreshold(0.50)`. Only the 2 most relevant chunks with at least 50% similarity are injected into the prompt. We use a lower threshold to ensure relevant context is not excluded by overly strict filtering.

"In the `ask()` method, we call the chat client and extract both the answer and the source documents. The response is a `RagResponse` record with the `answer` and a `sources` list — so the user can verify where the information came from."

## 4:30 – 5:30 | Running & Verification

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s "http://localhost:8080/ask?question=How+do+I+reset+my+device?" | jq`)*

"Let's test. We ask: 'How do I reset my device?' The response includes a detailed answer from the product manual, and the `sources` field confirms it came from `product-manual.txt`.

*(🖥️ Terminal: `curl -s "http://localhost:8080/ask?question=What+is+the+meaning+of+life?" | jq`)*

"Now let's test the guardrail. 'What is the meaning of life?' is not in our manual. The response is: 'I do not have enough information.' The LLM did not hallucinate — the system prompt prevented it."

## 5:30 – 6:00 | Outro

"To summarize: We built a complete RAG pipeline — ingestion with `TextReader` and `TokenTextSplitter`, grounded Q&A with `QuestionAnswerAdvisor`, and hallucination prevention with a system prompt guardrail. The `sources` field provides transparency, letting users verify where the answer came from.

"Great job if you got this working. I'll see you in the next module."
