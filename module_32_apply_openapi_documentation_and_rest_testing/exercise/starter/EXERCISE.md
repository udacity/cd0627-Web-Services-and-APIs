# Module 32 — API Documentation and Testing — Exercise Instructions

## Exercise Overview

Frontend developers are complaining that they don't know how to use your API. You need to auto-generate interactive Swagger UI documentation and write integration and validation tests.

---

## Prerequisites
- **Java 23+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Integration Test (`OrderIntegrationTest.java`)

1. Set up the integration test class with `@SpringBootTest` and `@AutoConfigureMockMvc` (Step 1).
2. Plan the test flow: POST to create an order, then GET to retrieve and assert persistence (Step 2).
3. Implement the POST: submit a `CreateOrderRequest` with valid `itemIds` and capture the returned `id` (Step 3).
4. Implement the GET: request `/orders/{id}` and assert **status 200** with correct body fields (Step 4).

### Validation Test (`OrderValidationTest.java`)

5. Write a `@WebMvcTest` for invalid payload scenarios (Step 5).
6. Plan assertions for **HTTP 400** and RFC 7807 problem detail fields (Step 6).
7. Submit `POST /orders` with `{"itemIds":[]}` (Step 7).
8. Assert status is **400 Bad Request** (Step 8).
9. Assert `jsonPath("$.type")` exists (Step 9).
10. Assert `jsonPath("$.title").value("Bad Request")` (Step 10).
11. Assert `jsonPath("$.detail")` contains "empty" (Step 11).

### OpenAPI Documentation (`OrderController.java`)

12. Add `@Operation` and `@ApiResponses` annotations on the `cancelOrder` endpoint to document the possible response codes (Step 12).

> [!NOTE]
> The `springdoc-openapi-starter-webmvc-ui` dependency is already in the `pom.xml`.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

```bash
mvn spring-boot:run
```

Access the Swagger UI:
```
http://localhost:8080/swagger-ui.html
```

Run the tests:
```bash
mvn test
```

---

## Success Criteria

- [ ] The integration test creates an order via POST and retrieves it via GET.
- [ ] The validation test confirms empty `itemIds` returns **400** with RFC 7807 `ProblemDetail`.
- [ ] The Swagger UI loads at `/swagger-ui.html` with custom descriptions.
- [ ] `@Operation` and `@ApiResponses` are visible in the Swagger UI for `cancelOrder`.
