# Module 32 - API Documentation and Testing - Exercise Instructions

## Exercise Overview

Frontend developers are complaining that they don't know how to use your API. You need to auto-generate an interactive Swagger UI documentation dashboard directly from your code.

---

## Prerequisites
- **Java 21+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | Implement the integration test | `src/test/java/com/ecommerce/docs/OrderIntegrationTest.java` |
| 2 | POST /orders to create, then GET /orders/{id} to retrieve and assert persistence | `src/test/java/com/ecommerce/docs/OrderIntegrationTest.java` |
| 3 | POST an order and capture the returned id | `src/test/java/com/ecommerce/docs/OrderIntegrationTest.java` |
| 4 | GET /orders/{id} and assert status 200 and body fields | `src/test/java/com/ecommerce/docs/OrderIntegrationTest.java` |
| 5 | Write a WebMvcTest for invalid payload | `src/test/java/com/ecommerce/docs/OrderValidationTest.java` |
| 6 | Assert HTTP 400 and RFC 7807 problem detail fields | `src/test/java/com/ecommerce/docs/OrderValidationTest.java` |
| 7 | Submit POST /orders with {"itemIds":[]} | `src/test/java/com/ecommerce/docs/OrderValidationTest.java` |
| 8 | Assert status is 400 Bad Request | `src/test/java/com/ecommerce/docs/OrderValidationTest.java` |
| 9 | Assert jsonPath("$.type") exists | `src/test/java/com/ecommerce/docs/OrderValidationTest.java` |
| 10 | Assert jsonPath("$.title").value("Bad Request") | `src/test/java/com/ecommerce/docs/OrderValidationTest.java` |
| 11 | Assert jsonPath("$.detail") contains "empty" | `src/test/java/com/ecommerce/docs/OrderValidationTest.java` |
| 12 | Add `@Operation` and `@ApiResponses` on cancelOrder documenting: | `src/main/java/com/ecommerce/docs/OrderController.java` |


> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] The Swagger UI loads successfully at `/swagger-ui.html`.
- [ ] The custom descriptions are visible in the UI.
