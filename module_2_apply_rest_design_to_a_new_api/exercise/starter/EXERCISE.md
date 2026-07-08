# Module 2 - REST Principles and HTTP Methods - Exercise Instructions

## Exercise Overview

You are joining a team building an e-commerce platform. The Product API is live, but the backend team needs a contract for the Order domain. You must map out the HTTP Methods, URIs, and Status Codes for fetching orders, fetching line items, and cancelling an order safely using standard REST annotations.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | 1 – GET /orders/{id} | `src/main/java/com/ecommerce/order/controller/OrderController.java` |
| 2 | implement this method | `src/main/java/com/ecommerce/order/controller/OrderController.java` |
| 3 | 2 – GET /orders/{id}/items | `src/main/java/com/ecommerce/order/controller/OrderController.java` |
| 4 | implement this method | `src/main/java/com/ecommerce/order/controller/OrderController.java` |
| 5 | 3 – POST /orders/{id}/cancel | `src/main/java/com/ecommerce/order/controller/OrderController.java` |
| 6 | implement this method | `src/main/java/com/ecommerce/order/controller/OrderController.java` |


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

- [ ] The controller handles GET and POST requests.
- [ ] Endpoints are mapped logically.
- [ ] Order cancellation safely mimics idempotency.
