# Solution Walkthrough: REST API Design (Module 3)

**Focus:** The Order API — GET, Sub-Resources, and Idempotent Cancellation
**Target Length:** 5 - 7 minutes
**File:** `OrderController.java`

---

## 0:00 – 1:00 | Introduction & The Exercise

*(Screen showing `OrderController.java` open in the IDE)*

"Welcome back. In this video, we're going to walk through the solution to the REST API Design exercise.

"Our goal was to implement three endpoints for an Order API: fetching an order by ID, fetching the items for an order as a sub-resource, and cancelling an order with idempotent behavior.

"Let's walk through each endpoint."

## 1:00 – 2:30 | Endpoint 1: GET /orders/{id}

*(Highlight lines 68-73: `getOrder()` method)*

"Our first endpoint is `GET /orders/{id}`. We use `@GetMapping("/{id}")` and accept the order ID as a `@PathVariable`.

"The implementation is straightforward. We call `repository.findById(id)`, which returns an `Optional<Order>`. If the order exists, we map it to `ResponseEntity.ok()` — that is a 200 OK. If it doesn't exist, we return `ResponseEntity.notFound().build()` — a 404.

"The key principle: never return an empty 200 for a missing resource. 404 is the honest answer."

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s http://localhost:8080/orders/1 | jq`)*

"Let's test it. `curl /orders/1` returns the full order object with a 200 OK. And if we try `/orders/99`..."

*(🖥️ Terminal: `curl -s -o /dev/null -w "%{http_code}" http://localhost:8080/orders/99`)*

"We get a 404. Exactly what we want."

## 2:30 – 4:00 | Endpoint 2: GET /orders/{id}/items

*(Highlight lines 95-100: `getOrderItems()` method)*

"Our second endpoint is `GET /orders/{id}/items`. This is a nested sub-resource — the items belong to the order, and the URI expresses that ownership explicitly.

"Notice the mapping: `@GetMapping("/{id}/items")`. The implementation follows the same pattern. We look up the order. If it exists, we extract its items with `order.getItems()` and return them with a 200. If the parent order doesn't exist, we return 404.

"There is an important subtlety here. If the order exists but has no items, we return 200 with an empty array. An empty list is a perfectly valid state — the resource exists, it just has no children."

*(🖥️ Terminal: `curl -s http://localhost:8080/orders/1/items | jq`)*

"Testing it: `curl /orders/1/items` returns the line items for order 1."

## 4:00 – 5:30 | Endpoint 3: POST /orders/{id}/cancel

*(Highlight lines 134-161: `cancelOrder()` method)*

"The third endpoint is the most interesting: `POST /orders/{id}/cancel`. 

"Why is this POST and not PATCH? Cancellation is a business action that triggers side effects — refunds, inventory restocking, notification emails. PATCH implies a simple data-field update; it does not communicate that intent.

*(Highlight lines 140-146: Idempotency guard)*

"Now, look at the first thing we check. If the order's status is already `CANCELLED`, we return 200 OK immediately with a message saying 'already cancelled, no further action taken.' We do not re-trigger the refund, the restock, or the email. This is idempotency — calling the endpoint multiple times produces the same result and prevents double-refunds.

*(Highlight lines 149-158: First cancellation path)*

"If the order has not been cancelled yet, we run the business logic. We call `order.cancel()` to transition the state, then we simulate the refund, the restock, and the email notification.

*(🖥️ Terminal: `curl -s -X POST http://localhost:8080/orders/1/cancel | jq`)*

"Let's test it. The first call to `POST /orders/1/cancel` returns 'Order 1 successfully cancelled. Refund initiated.' And in the server logs, we can see the simulated side effects firing.

*(🖥️ Terminal: `curl -s -X POST http://localhost:8080/orders/1/cancel | jq`)*

"If we call it a second time, we get 'Order 1 was already cancelled. No further action taken.' — and critically, no side effects run again. Idempotent."

## 5:30 – 6:00 | Outro

"To summarize: We implemented three REST endpoints with proper status codes, sub-resource nesting, and idempotent business actions. The key takeaway is that REST is not just about CRUD — when an operation has side effects, use POST with a named action path, and always build idempotency into your design.

"Great job if you got this working. I'll see you in the next module."
