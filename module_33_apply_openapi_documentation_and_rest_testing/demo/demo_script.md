# Demo Walkthrough: OpenAPI Documentation and REST Testing (Module 33)

**Focus:** From Undocumented Endpoints to Interactive Swagger UI and Automated Tests
**Target Length:** 5 - 7 minutes
**Files:** `OrderController.java`, `OrderIntegrationTest.java`

---

## 0:00 – 1:00 | Introduction & The Problem

*(Screen showing `OrderController.java` open in the IDE)*

"Welcome back. In this demo — the final module — we are going to look at OpenAPI Documentation and REST API Testing.

"Without documentation, consumers of your API have to guess how to use it — what endpoints exist, what parameters they take, and what error codes they return. And without automated tests, you have no way to verify that your API actually works as documented.

"We are going to solve both problems: OpenAPI annotations for interactive documentation, and Spring's MockMvc for automated integration tests."

## 1:00 – 2:30 | OpenAPI Annotations

*(Highlight lines 32-40: `@Operation` and `@ApiResponses` on `cancelOrder`)*

"SpringDoc OpenAPI auto-generates documentation from your Spring controllers. But for complex endpoints, the auto-generated docs may not capture all the error scenarios.

"Look at the `cancelOrder` endpoint. We add `@Operation(summary = "Cancel an order by ID")` to describe it. Then `@ApiResponses` lists every possible response: 204 No Content for success, 404 Not Found if the order does not exist, and 422 Unprocessable Entity if the order has already been shipped.

"Each response includes a `@Content` annotation with a `@Schema` reference to `ProblemDetail.class`. This tells the documentation exactly what the error response body looks like.

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s http://localhost:8080/v3/api-docs | jq '.paths'`)*

"Let's run the app and fetch the OpenAPI spec. Every endpoint is documented with request/response schemas. The cancel endpoint shows all three possible responses — 204, 404, and 422. This JSON spec is what powers tools like Swagger UI and client code generators."

## 2:30 – 4:00 | Integration Testing with MockMvc

*(Switch tabs to `OrderIntegrationTest.java`)*

"Now let's look at the tests. `OrderIntegrationTest` is annotated with `@SpringBootTest` and `@AutoConfigureMockMvc`. This boots the full application context and provides `MockMvc` — a test client for making HTTP requests without a running server.

"The test method `createAndRetrieveOrder` does exactly what a real client would: it POSTs an order, asserts the response is 201 Created, extracts the generated ID from the JSON response, then GETs that order and asserts the status is 'CREATED'.

"Notice we use `jsonPath()` matchers — `$.id`, `$.status` — to assert on specific fields in the JSON response. This is much cleaner than parsing the JSON manually."

## 4:00 – 5:00 | Validation Testing

*(Switch tabs to `OrderValidationTest.java`)*

"The `OrderValidationTest` uses `@WebMvcTest` instead of `@SpringBootTest`. This loads only the web layer — the controller and its exception handler — with `@MockBean` for the service. It is faster because it does not boot the full application.

"The test sends an empty `itemIds` array and asserts: status is 400 Bad Request, the response has a `type` field (RFC 7807), the title is 'Bad Request', and the detail message contains 'Invalid request content'.

*(🖥️ Terminal: `mvn test`)*

"Let's run the tests. Both pass — our API works as documented."

## 5:00 – 5:30 | Outro & Summary

"To summarize: OpenAPI annotations generate interactive documentation with Swagger UI. `@SpringBootTest` with `MockMvc` provides full integration tests. And `@WebMvcTest` provides fast, isolated controller tests. Together, these ensure your API is documented, tested, and reliable.

"This marks the end of the course modules. Great work on completing all the exercises. Thanks for watching!"
