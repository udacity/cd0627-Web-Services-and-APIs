# Solution Walkthrough: OpenAPI Documentation and REST Testing (Module 33)

**Focus:** API Documentation, Integration Tests for State Transitions, and Error Mocking
**Target Length:** 5 - 7 minutes
**Files:** `OrderController.java`, `OrderIntegrationTest.java`, `OrderNotFoundTest.java`

---

## 0:00 – 1:00 | Introduction & The Exercise

*(Screen showing the project open in the IDE)*

"Welcome back. In this video, we're going to walk through the solution to the OpenAPI Documentation and REST Testing exercise.

"Our goal was to add OpenAPI annotations to the `getOrder` endpoint, write an integration test for the create-cancel-verify flow, and write a slice test that mocks a service exception to test 404 error handling."

## 1:00 – 2:30 | OpenAPI Annotations

*(Switch tabs to `OrderController.java`, highlight the `@Operation` and `@ApiResponses` annotations on `getOrder`)*

"In the OrderController, we annotate `getOrder` using `@Operation(summary = "Retrieve an order by ID")` and `@ApiResponses` to document two outcomes: 200 OK with a schema of `Order.class`, and 404 Not Found with a `ProblemDetail.class` schema.

"Each `@ApiResponse` uses `@Content` with `@Schema` to specify the response body type. The 200 response maps to `Order.class`, and the 404 maps to `ProblemDetail.class` — so API consumers see the full contract in Swagger UI."

## 2:30 – 4:00 | The Integration Test

*(Switch tabs to `OrderIntegrationTest.java`)*

"The integration test uses `@SpringBootTest` with `@AutoConfigureMockMvc`. This boots the full application — including the database and service layer.

"We test a state transition: create an order, cancel it, then retrieve it and verify the status changed to `CANCELLED`. This is a three-step flow — POST to create, POST to cancel, GET to verify.

"We extract the order ID from the create response using `JsonPath.read()`, then use it in the cancel and retrieve requests. The key assertion is `jsonPath("$.status").value("CANCELLED")` — proving that the cancel operation actually persisted the state change."

## 4:00 – 5:00 | The Not-Found Slice Test

*(Switch tabs to `OrderNotFoundTest.java`)*

"The slice test uses `@WebMvcTest(OrderController.class)` — a fast test that loads only the controller and exception handler. The `OrderService` is replaced with a `@MockBean`, so no database is needed.

"We use `Mockito.when()` to configure the mock `OrderService` to throw `OrderNotFoundException` when called with a fake ID. The error comes from the service layer, so we need Mockito to simulate it.

"We assert the response is 404 Not Found with an RFC 7807 ProblemDetail body — `type` exists, `title` is 'Not Found', and `detail` contains the error message."

## 5:00 – 5:30 | Running All Tests

*(🖥️ Terminal: `mvn test`)*

"Let's run the tests. Both pass — the integration test validates the full create-cancel-verify lifecycle, and the slice test confirms the 404 error response.

*(🖥️ Terminal: `curl -s http://localhost:8080/v3/api-docs | jq '.paths'`)*

"And the OpenAPI spec documents both the success and error responses for the `getOrder` endpoint."

## 5:30 – 6:00 | Outro

"To summarize: OpenAPI annotations with `@Operation` and `@ApiResponses` generate interactive Swagger documentation. Integration tests with `@SpringBootTest` verify end-to-end state transitions. Slice tests with `@WebMvcTest` and Mockito verify error handling in isolation — fast and focused.

"Thanks for watching!"
