# Exercise – Designing the Order API Contract

> **Time:** 10–20 minutes  
> **Your task:** Design the REST API contract for the Order domain of an e-commerce system.  
> You will **not** write Java code today — you are writing the *contract* that the code must honour.

---

## Background

You are joining a team building an e-commerce platform. The Product API is already live
(you just watched the demo). Now the backend team needs a contract for the **Order** domain
before any code is written.

> **Remember:** A flawed contract means fundamentally broken code.  
> Think carefully about each decision.

---

## Constraints

For **each** endpoint you design, you must provide:

| Field | What to fill in |
|---|---|
| HTTP Method | `GET`, `POST`, `PUT`, `PATCH`, or `DELETE` |
| URI Path | e.g. `/orders/{id}` |
| Success Status Code | e.g. `200`, `201`, `204` … |
| One Error Status Code | e.g. `404`, `400`, `409` … |
| Brief Reason | 1–2 sentences explaining your choices |

---

## Task 1 – Standard Retrieval

**Scenario:** A customer wants to see the details of a specific order.

> **Design the endpoint that fetches a single Order by its ID.**

| Field | Your Answer |
|---|---|
| HTTP Method | *(fill in)* |
| URI Path | *(fill in)* |
| Success Status Code | *(fill in)* |
| One Error Status Code | *(fill in — what happens if the order id doesn't exist?)* |
| Brief Reason | *(fill in)* |

---

## Task 2 – The Nested Resource (Hierarchy)

**Scenario:** An order contains multiple line items (products + quantities). A mobile app
wants to display *only the items* for a given order — not the full order details.

> **Design the endpoint that returns all items belonging to a specific order.**

| Field | Your Answer |
|---|---|
| HTTP Method | *(fill in)* |
| URI Path | *(fill in — hint: items live inside an order)* |
| Success Status Code | *(fill in)* |
| One Error Status Code | *(fill in)* |
| Brief Reason | *(fill in)* |

---

## Task 3 – The State Transition (Action)

**Scenario:** A customer wants to cancel an order. The business rules say:
- The system must issue a refund to the payment provider.
- Inventory must be restocked for each item.
- A cancellation confirmation email must be sent.
- If the order is already cancelled, the operation should be safe to call again
  (**idempotent** — no double-refund).

> **Design the endpoint that handles order cancellation.**
>
> *Hint: Think about whether a standard `PATCH /orders/{id}` with `{"status": "CANCELLED"}`
> is sufficient here, or whether the complex business logic demands a different approach.*

| Field | Your Answer |
|---|---|
| HTTP Method | *(fill in)* |
| URI Path | *(fill in)* |
| Success Status Code | *(fill in)* |
| One Error Status Code | *(fill in — what if the order doesn't exist?)* |
| Brief Reason | *(fill in)* |

**Bonus question – Idempotency:**  
Can your cancel endpoint be safely called **twice** without issuing a double-refund?  
Explain how your design guarantees (or fails to guarantee) this.

```
Your answer:
```

---

## Reflection

Once you have filled in all three tasks, consider:

1. Why didn't you use `DELETE /orders/{id}` to cancel an order?
2. What is the difference between a *state transition* and a *data update*?
3. If cancellation returned `204 No Content` instead of `200 OK`, would that be
   more or less useful to the caller? Why?

```
Your answers:
```
