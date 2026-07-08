# Module 4 - Building REST APIs with Spring Boot - Exercise Instructions

## Exercise Overview

Your startup needs a REST API to manage Customers. You need to build a Spring Boot REST API that handles CRUD operations for a Customer entity, utilizes API versioning, and communicates with a downstream service using an HTTP client.

---

## Prerequisites
- **Java 21+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | 1: Implement GET /orders/{id}. Use ResponseEntity.ok(). Include customer name via customerClient. | `src/main/java/com/ecommerce/order/controller/OrderController.java` |
| 2 | 2: Implement POST /orders. Use ResponseEntity.created() to return 201 Created. | `src/main/java/com/ecommerce/order/controller/OrderController.java` |
| 3 | 3: Implement POST /orders/{id}/cancel. Return 404 if order not found (mock it by checking id > 100), otherwise return 204 No Content. | `src/main/java/com/ecommerce/order/controller/OrderController.java` |
| 4 | 4: Implement GET /orders/{id} for version 2 (e.g. headers="version=2"). Add an "orderSummary" field to the response. | `src/main/java/com/ecommerce/order/controller/OrderController.java` |
| 5 | Configure GetExchange for /internal/customers/{id} | `src/main/java/com/ecommerce/order/client/CustomerClient.java` |


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

- [ ] HTTP GET returns customer data.
- [ ] HTTP POST successfully creates a resource.
- [ ] HTTP interface is configured correctly.
