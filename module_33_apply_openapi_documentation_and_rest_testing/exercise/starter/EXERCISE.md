# Module 33 — API Documentation and Testing — Exercise Instructions

## Exercise Overview

Frontend developers are complaining that they don't know how to use your API. You need to auto-generate interactive Swagger UI documentation and write integration and slice tests.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Integration Test (`OrderIntegrationTest.java`)

1. Set up the integration test class with `@SpringBootTest` and `@AutoConfigureMockMvc` (Step 1).
2. Plan the test flow: POST to create an order, cancel it, then GET to verify the status changed (Step 2).
3. Implement the POST: submit a `CreateOrderRequest` with valid `itemIds` and capture the returned `id` (Step 3).
4. Implement the cancel: POST to `/orders/{id}/cancel` and assert **status 204 No Content** (Step 4).
5. Implement the GET: request `/orders/{id}` and assert **status 200** with `status` field equal to `"CANCELLED"` (Step 5).

### Not Found Test (`OrderNotFoundTest.java`)

6. Write a `@WebMvcTest` for the not-found scenario (Step 6).
7. Use `Mockito.when()` to make `orderService.getOrder("FAKE-999")` throw `OrderNotFoundException` (Step 7).
8. Perform `GET /orders/FAKE-999` and assert status is **404 Not Found** (Step 8).
9. Assert `jsonPath("$.type")` exists, `jsonPath("$.title").value("Not Found")`, and `jsonPath("$.detail")` exists.

### OpenAPI Documentation (`OrderController.java`)

10. Add `@Operation` and `@ApiResponses` annotations on the `getOrder` endpoint to document the possible response codes: **200 OK** (schema = `Order.class`) and **404 Not Found** (schema = `ProblemDetail.class`) (Step 9).

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

- [ ] The integration test creates an order, cancels it, and verifies the status is `CANCELLED`.
- [ ] The not-found test confirms a non-existent order returns **404** with RFC 7807 `ProblemDetail`.
- [ ] The Swagger UI loads at `/swagger-ui.html` with custom descriptions.
- [ ] `@Operation` and `@ApiResponses` are visible in the Swagger UI for `getOrder`.
