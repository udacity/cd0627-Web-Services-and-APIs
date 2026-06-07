# AI-Powered Automated RMA Engine (Capstone Project)

Welcome to the capstone project! In this project, you will build an event-driven, AI-powered Return Merchandise Authorization (RMA) engine. 

You will use **Spring Boot 4.0**, **Spring AI**, **Resilience4j**, **Kafka (Embedded)**, and **GraphQL**.

---

## 🏗 Architecture Overview

```text
       ┌─────────────┐
       │             │ 1. POST /api/returns
       │   Client    ├─────────────────────────────────┐
       │             │                                 │
       └─────────────┘                                 ▼
              ▲                                ┌───────────────┐
              │                                │ RmaController │
              │                                │ (@CircuitBreaker)
              │                                └───────┬───────┘
              │ 6. Query GraphQL                       │
              │    returnsDashboard                    │ 2. processReturn()
              │                                        ▼
       ┌──────┴──────┐                         ┌───────────────┐
       │  Dashboard  │                         │               │
       │ Controller  │                         │               │   3. BeanOutputConverter
       └──────┬──────┘                         │               │◄─────────────────────────┐
              │                                │               │                          │
              │                                │  RmaService   │                          ▼
              │                                │               │                   ┌─────────────┐
              │                                │               │   4. RAG Advisor  │   OpenAI    │
              │                                │               │◄─────────────────►│   (LLM)     │
              │                                │               │                   └─────────────┘
              │                                └───────┬───────┘                          ▲
              │                                        │                                  │
              │                                        │ 5. KafkaTemplate.send()          │ (Embeddings)
       ┌──────┴──────┐                                 ▼                                  ▼
       │             │                         ┌───────────────┐                   ┌─────────────┐
       │  Dashboard  │◄────────────────────────┤  Kafka Topic  │                   │ VectorStore │
       │   Service   │   6. @KafkaListener     │               │                   │ (In-Memory) │
       │             │                         └───────────────┘                   └─────────────┘
       └─────────────┘
```

---

## 🚀 Environment Setup

1. **API Key:** Ensure your Vocareum environment has injected the `OPENAI_API_KEY` into your terminal.
   ```bash
   echo $OPENAI_API_KEY
   ```
2. **Java Version:** This project requires JDK 25 LTS.
3. **No Docker Required:** Kafka runs completely inside JVM memory via `EmbeddedKafkaBroker`. The vector database is an in-memory `SimpleVectorStore`.

---

## 📝 Step-by-Step Implementation Guide

Open the project in your IDE. You will find `TODO` comments scattered throughout the code. Complete them in the following order:

### Step 1: DTOs & The REST Controller
1. Open `ReturnRequest.java` and `ReturnResponse.java` in `com.ecommerce.rma.dto` to familiarise yourself with the data structures.
2. Open `RmaController.java`. Add the missing `@PostMapping`, `@Operation`, and `@CircuitBreaker` annotations.
3. Implement the circuit breaker fallback method in `RmaController`.

### Step 2: Write Tests
1. Open `RmaControllerTest.java` and complete the unit test for the denied-return scenario.
2. Open `RmaServiceIntegrationTest.java` and implement the end-to-end asynchronous test using Awaitility.

### Step 3: AI Complaint Analysis (Structured Output)
1. Open `ReturnAnalysis.java` and review the structure.
2. Open `RmaService.java` and implement Step 3: Create a `BeanOutputConverter`, build a prompt with the converter's JSON format instructions, call the `ChatClient`, and convert the response into a `ReturnAnalysis` object.

### Step 4: Policy Check (Retrieval-Augmented Generation)
1. Open `AiConfig.java` and implement the `@PostConstruct` method to read `policy-seed.txt` and load it into the `VectorStore`.
2. Return to `RmaService.java` and implement Step 4: Create a `QuestionAnswerAdvisor`, build a policy question based on the analysis from Step 3, call the `ChatClient`, and determine if the return is approved.

### Step 5: Kafka Event Publishing (CQRS Write Side)
1. Open `ReturnApprovedEvent.java` and review the payload.
2. Return to `RmaService.java` and implement Step 5: If the return was approved in Step 4, construct a `ReturnApprovedEvent` and publish it using `kafkaTemplate.send()`.

### Step 6: GraphQL Dashboard (CQRS Read Side)
1. Open `schema.graphqls` in `src/main/resources/graphql` and review the schema.
2. Open `DashboardController.java` and annotate the method with `@QueryMapping`.
3. Open `DashboardService.java` and implement the `@KafkaListener` to consume events from Kafka and store them in the `dashboardView` map.

---

## 🛠 How to Build & Run

### 1. Build the project
```bash
./mvnw clean install
```

### 2. Run the tests
```bash
./mvnw test
```

### 3. Start the application
```bash
./mvnw spring-boot:run
```

---

## 🧪 Manual Verification

Once the application is running, you can manually verify the functionality.

### 1. Submit a Return Request (cURL)
```bash
curl -X POST http://localhost:8080/api/returns \
  -H "Content-Type: application/json" \
  -d '{
        "customerId": "CUST-999",
        "complaintText": "The zipper on this jacket is completely broken and tears the fabric."
      }'
```
*Expected Output:* You should receive a JSON response with `"approved": true` and an AI-generated reason.

### 2. Query the GraphQL Dashboard
Open your browser and navigate to the GraphiQL playground:
**http://localhost:8080/graphiql**

Execute the following query to view the approved returns:
```graphql
query {
  returnsDashboard {
    customerId
    itemType
    reason
  }
}
```

### 3. View the OpenAPI Specs
Navigate to the Swagger UI:
**http://localhost:8080/swagger-ui.html**
