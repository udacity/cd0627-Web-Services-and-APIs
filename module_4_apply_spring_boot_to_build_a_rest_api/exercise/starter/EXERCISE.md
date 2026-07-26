# Module 4 — Building REST APIs with Spring Boot — Exercise Instructions

## Exercise Overview

Your startup needs a REST API to manage Orders. You will build a Spring Boot REST API with CRUD-style endpoints for an Order resource, implement API versioning via headers, and use an HTTP interface to call a downstream Customer service.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

1. In `src/main/java/com/ecommerce/order/controller/OrderController.java`, implement `GET /orders/{id}` using `ResponseEntity.ok()`. Include the customer name by calling `customerClient.getCustomerName(id)`.
2. Implement `POST /orders` using `ResponseEntity.created(URI.create("/orders/1"))` to return **201 Created**.
3. Implement `POST /orders/{id}/cancel` — return **404 Not Found** if `id > 100` (mock check), otherwise return **204 No Content**.
4. Implement a **versioned** `GET /orders/{id}` using `headers="version=2"`. Add an `"orderSummary"` field to the response.
5. In `src/main/java/com/ecommerce/order/client/CustomerClient.java`, configure a `@GetExchange` for `/internal/customers/{id}` so the HTTP interface proxy can call the downstream customer service.

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

- [ ] `GET /orders/1` returns order data with a `customerName` field.
- [ ] `POST /orders` returns **201 Created** with a `Location` header.
- [ ] `POST /orders/200/cancel` returns **404 Not Found** (id > 100).
- [ ] `POST /orders/50/cancel` returns **204 No Content**.
- [ ] `GET /orders/1` with header `version=2` includes an `orderSummary` field.
- [ ] `CustomerClient` HTTP interface is configured with `@GetExchange`.
