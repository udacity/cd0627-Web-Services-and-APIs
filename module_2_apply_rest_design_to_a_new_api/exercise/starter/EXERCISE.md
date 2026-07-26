# Module 2 — REST API Design — Exercise Instructions

## Exercise Overview

You are designing a RESTful API for an e-commerce order system. You need to implement endpoints that follow REST conventions for resource naming, HTTP methods, and status codes.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

1. In `src/main/java/com/ecommerce/order/controller/OrderController.java`, review the existing `@GetMapping` and `@PostMapping` annotations on the scaffolded methods (Step 1).

2. Implement the **`GET /orders/{id}`** method body (Step 2). Look up the order from the in-memory store and return it with `ResponseEntity.ok()`. Return 404 if not found.

3. Implement the **`GET /orders/{id}/items`** method body (Step 4). Return the items for a given order as a sub-resource. Return 404 if the order doesn't exist.

4. Implement the **`POST /orders/{id}/cancel`** method body (Step 6). Check preconditions (order exists, is not already cancelled), update the status, and return the appropriate HTTP status code (200 on success, 404/409 on failure).

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

```bash
mvn spring-boot:run
```

Test with:
```bash
curl http://localhost:8080/orders/1
curl http://localhost:8080/orders/1/items
curl -X POST http://localhost:8080/orders/1/cancel
```

---

## Success Criteria

- [ ] `GET /orders/{id}` returns order data with proper status codes.
- [ ] `GET /orders/{id}/items` returns sub-resource items for the order.
- [ ] `POST /orders/{id}/cancel` safely handles cancellation (idempotent behavior).
- [ ] Non-existent orders return **404 Not Found**.
