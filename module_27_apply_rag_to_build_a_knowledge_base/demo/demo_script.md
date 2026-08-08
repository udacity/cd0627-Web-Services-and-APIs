# Demo Walkthrough: Retrieval-Augmented Generation (Module 27)

**Focus:** From LLM Hallucination to Grounded Answers with RAG
**Target Length:** 5 - 7 minutes
**Files:** `RagRunner.java`, `RagConfig.java`

---

## Prerequisites

Before running, export your OpenAI API key:

```bash
export OPENAI_API_KEY=<your-openai-api-key>
```

---

## 0:00 – 1:00 | Introduction & The Problem

*(Screen showing `RagRunner.java` open in the IDE)*

"Welcome back. In this demo, we are going to look at Retrieval-Augmented Generation — RAG.

"Here is the problem: LLMs are trained on general knowledge, but they do not know about your specific product manuals, internal policies, or company data. If you ask an LLM 'What is the warranty policy for our Widget Pro?', it will either make something up — this is called hallucination — or say it does not know.

"RAG solves this by retrieving relevant information from your knowledge base and injecting it into the prompt before the LLM generates a response. The LLM now has the actual facts to work with."

## 1:00 – 2:30 | The RAG Pipeline

*(Highlight `RagConfig.java`)*

"The RAG pipeline has three stages. First, we need a Vector Store — we create a `SimpleVectorStore` backed by the embedding model. This is where we store our knowledge base.

*(Switch tabs to `RagRunner.java`, highlight the constructor)*

"Second, we attach a `QuestionAnswerAdvisor` to the `ChatClient`. The advisor is the key component — it automatically searches the vector store for relevant documents and injects them into the prompt context.

"When a user asks a question, the advisor finds the most relevant chunks of your knowledge base, adds them to the prompt as context, and the LLM generates an answer based on that context — not its general training data."

## 2:30 – 4:00 | Seeing RAG in Action

*(Highlight the `run()` method)*

"In the `run()` method, we first ingest our knowledge base — a product manual — into the vector store. We split it by blank lines into logical sections and add each as a `Document`. This is Step 1.

"Then in Step 2, we ask: 'What is the main advantage of semantic search?' The `QuestionAnswerAdvisor` searches the vector store, finds the relevant section from our product manual, and injects it into the prompt. The LLM generates a grounded answer.

"In Step 3, we ask a completely different question: 'What is the warranty policy for the Widget Pro?' Same pipeline, different context retrieved. The answer comes from the warranty section of our manual.

*(🖥️ Terminal: `mvn spring-boot:run`)*

"Let's run it. Looking at the console output — the semantic search answer references our specific product documentation, not generic LLM knowledge. And the warranty answer cites the exact policy from our manual: 2-year limited warranty, contact support@widgetpro.com."

## 4:00 – 5:00 | Why RAG Matters

"Without RAG, the LLM would either hallucinate a warranty policy or say it does not know. With RAG, the answer is grounded in our actual documentation.

"This is the pattern used by every enterprise AI assistant — customer support bots, internal knowledge bases, product documentation search. The LLM provides the language capability, and your data provides the facts."

## 5:00 – 5:30 | Outro & Summary

"To summarize: RAG combines semantic search with LLM reasoning. The `QuestionAnswerAdvisor` automatically retrieves relevant context from the vector store and injects it into the prompt. The result is accurate, grounded answers based on your specific data — eliminating hallucination.

"Thanks for watching, and I'll see you in the next module."
