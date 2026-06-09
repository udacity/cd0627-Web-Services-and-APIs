# End-to-End Testing Guide

This guide verifies the application manually from the outside: REST request in, AI or mock decision made, approved events published to embedded Kafka, Kafka consumer updates the dashboard, and GraphQL/OpenAPI surfaces respond correctly.

The app lives in this directory:

```bash
cd project
```

## 1. Prerequisites

Confirm the project can be run locally:

```bash
java --version
```

Expected:

- Java is installed.
- The project README says this project targets JDK 25.

You do not need Docker. Kafka is started in-process by `KafkaLocalConfig` on `localhost:9092`.

## 2. Choose Testing Mode

For deterministic manual testing, use mock mode. Mock mode is active when `OPENAI_API_KEY` is unset, blank, or equal to `mock-key`.

Mock mode uses keyword rules in `RmaService`:

- Complaints containing words like `broken`, `cracked`, `defect`, `torn`, `zipper`, or `screen` are treated as defective.
- Defective returns are approved.
- Complaints containing `changed my mind` or `change of mind` are denied.
- Approved returns publish a `ReturnApprovedEvent` to Kafka.
- Denied returns do not publish an event.

If you want to test against the real OpenAI-backed flow instead, set a real key before startup:

```bash
export OPENAI_API_KEY="your-real-key"
```

Expected outputs in live AI mode may vary in wording, but the status codes and overall flow should be the same.

## 3. Start the Application

From the `project` directory, start Spring Boot:

```bash
./mvnw spring-boot:run
```

For deterministic mock-mode testing even if your shell normally has an API key:

```bash
env -u OPENAI_API_KEY ./mvnw spring-boot:run
```

Expected startup behavior:

- The application starts on `http://localhost:8080`.
- Embedded Kafka starts on `localhost:9092`.
- In mock mode, startup logs should include a message similar to:

```text
[AiConfig] OPENAI_API_KEY is not set or is 'mock-key'. Skipping vector store seeding to allow offline/mock runs.
```

If startup fails because port `8080` or `9092` is already in use, stop the process using that port and restart the application.

## 4. Verify the REST Approval Flow

Submit a return request that should be approved:

```bash
curl -i -X POST http://localhost:8080/api/returns \
  -H "Content-Type: application/json" \
  -d '{
        "customerId": "CUST-E2E-APPROVED",
        "complaintText": "The zipper on this jacket is completely broken and tore the fabric."
      }'
```

Expected HTTP response:

```text
HTTP/1.1 200
```

Expected JSON body in mock mode:

```json
{
  "approved": true,
  "reason": "APPROVED: Defective clothing item eligible for refund per policy guidelines."
}
```

Expected backend behavior:

- `RmaController` accepts `POST /api/returns`.
- `RmaService` approves the defective clothing complaint.
- `RmaService` publishes a `ReturnApprovedEvent` to Kafka topic `returns-topic`.
- `DashboardService` consumes the event.
- Application logs should include something similar to:

```text
[DashboardService] Stored approved return: ReturnApprovedEvent[customerId=CUST-E2E-APPROVED, itemType=clothing, reason=APPROVED: Defective clothing item eligible for refund per policy guidelines.]
```

## 5. Verify the REST Denial Flow

Submit a return request that should be denied:

```bash
curl -i -X POST http://localhost:8080/api/returns \
  -H "Content-Type: application/json" \
  -d '{
        "customerId": "CUST-E2E-DENIED",
        "complaintText": "I changed my mind about this jacket and want to return it."
      }'
```

Expected HTTP response:

```text
HTTP/1.1 200
```

Expected JSON body in mock mode:

```json
{
  "approved": false,
  "reason": "DENIED: Change-of-mind returns are not accepted per company policy."
}
```

Expected backend behavior:

- The request receives a normal `200` response because a denial is a valid business decision, not an application error.
- No Kafka event is published for this denied return.
- `CUST-E2E-DENIED` should not appear in the dashboard.

## 6. Verify the GraphQL Dashboard

Because Kafka consumption is asynchronous, wait a second or two after the approved REST request, then query GraphQL.

Option A: use GraphiQL in the browser:

```text
http://localhost:8080/graphiql
```

Run this query:

```graphql
query {
  returnsDashboard {
    customerId
    itemType
    reason
  }
}
```

Option B: use `curl`:

```bash
curl -s -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{"query":"query { returnsDashboard { customerId itemType reason } }"}'
```

Expected response after the approved request:

```json
{
  "data": {
    "returnsDashboard": [
      {
        "customerId": "CUST-E2E-APPROVED",
        "itemType": "clothing",
        "reason": "APPROVED: Defective clothing item eligible for refund per policy guidelines."
      }
    ]
  }
}
```

The dashboard may contain additional entries if you submitted other approved returns during the same app run. The important checks are:

- `CUST-E2E-APPROVED` appears.
- `itemType` is `clothing`.
- `reason` starts with `APPROVED`.
- `CUST-E2E-DENIED` does not appear.

## 7. Verify OpenAPI and Swagger

Open Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

Expected:

- Swagger UI loads successfully.
- It lists the Returns API.
- `POST /api/returns` appears with the summary `Submit a return request`.
- The documented success response is `200`.
- The documented fallback/unavailable response is `503`.

You can also verify the raw OpenAPI JSON:

```bash
curl -s http://localhost:8080/api-docs
```

Expected:

- The response is JSON.
- It includes a path entry for `/api/returns`.

## 8. Optional Live OpenAI Verification

To test the real AI/RAG path, stop the app, export a real API key, and restart:

```bash
export OPENAI_API_KEY="your-real-key"
./mvnw spring-boot:run
```

Expected startup behavior:

- `AiConfig` seeds the in-memory vector store from `src/main/resources/policy-seed.txt`.
- Logs should include:

```text
[AiConfig] VectorStore successfully seeded with policy documents.
```

Repeat the approved and denied REST requests.

Expected live-mode behavior:

- Approved/denied decisions should follow the policy in `policy-seed.txt`.
- The exact `reason` text may differ because it is generated by the model.
- Approved returns should still appear in the GraphQL dashboard.
- Denied returns should still be absent from the dashboard.

## 9. End-to-End Pass Criteria

The project is working end to end when all of these are true:

- `POST /api/returns` returns `200` for both approved and denied business decisions.
- A defective-item complaint returns `"approved": true`.
- A change-of-mind complaint returns `"approved": false`.
- Approved returns are consumed by `DashboardService` and appear in `returnsDashboard`.
- Denied returns do not appear in `returnsDashboard`.
- GraphiQL is available at `/graphiql`.
- Swagger UI is available at `/swagger-ui.html`.
- No external Kafka or Docker process is required.
