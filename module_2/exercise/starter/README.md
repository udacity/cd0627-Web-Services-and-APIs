# Module 2 Exercise – Order API (Starter)

## Your Task

Implement three REST endpoints for the **Order** domain of an e-commerce system.  
All the scaffolding (models, repository, DTOs) is already in place.  
Your work is entirely in **`OrderController.java`**.

---

## Endpoints to Implement

| # | Method | URI | Success | Error |
|---|---|---|---|---|
| 1 | `GET` | `/orders/{id}` | 200 OK | 404 Not Found |
| 2 | `GET` | `/orders/{id}/items` | 200 OK | 404 Not Found |
| 3 | `POST` | `/orders/{id}/cancel` | 200 OK | 404 Not Found |

Open [`OrderController.java`](src/main/java/com/ecommerce/order/controller/OrderController.java)  
and follow the `TODO` comments for each method.

---

## How to Run

```bash
# From the exercise/starter directory:
./mvnw spring-boot:run
```

Two orders are pre-seeded — test immediately with:

```bash
# Task 1: fetch an order
curl -i http://localhost:8080/orders/1

# Task 1: trigger 404
curl -i http://localhost:8080/orders/99

# Task 2: fetch items for an order
curl -i http://localhost:8080/orders/1/items

# Task 3: cancel an order
curl -i -X POST http://localhost:8080/orders/1/cancel

# Task 3: cancel again → should NOT double-refund (idempotency)
curl -i -X POST http://localhost:8080/orders/1/cancel
```

---

## Reflection Question

After completing TODO 3, answer this in a comment in the code:

> **Why did you use `POST /orders/{id}/cancel` instead of  
> `PATCH /orders/{id}` with `{"status": "CANCELLED"}`?**
