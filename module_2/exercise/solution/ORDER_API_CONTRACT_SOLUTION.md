# Exercise Solution – Order API Contract

> **For instructor / reviewer use only. Do not distribute to students before the exercise.**

---

## Task 1 – Standard Retrieval

**Endpoint: Fetch a specific Order by ID**

| Field | Answer |
|---|---|
| HTTP Method | `GET` |
| URI Path | `/orders/{id}` |
| Success Status Code | **200 OK** |
| One Error Status Code | **404 Not Found** |
| Brief Reason | `GET` is the correct verb for a safe, read-only retrieval. `{id}` uniquely addresses the resource. `200` confirms the resource was found and returned. `404` is the only honest answer when no order with that id exists — returning an empty `200` would be misleading. |

---

## Task 2 – The Nested Resource (Hierarchy)

**Endpoint: Fetch items belonging to a specific Order**

| Field | Answer |
|---|---|
| HTTP Method | `GET` |
| URI Path | `/orders/{id}/items` |
| Success Status Code | **200 OK** |
| One Error Status Code | **404 Not Found** |
| Brief Reason | Items are a sub-collection owned by an order, so they live under the order's URI. If the parent order does not exist, `404` is correct. If the order exists but has no items, return `200` with an empty array `[]` — that is a valid (and meaningful) state. |

> **Discussion note for instructors:**  
> A common student mistake is `/items?orderId=123`. Remind them that nested resources
> (`/orders/123/items`) express the *ownership* relationship directly in the path,
> which is the RESTful convention and makes authorization scoping simpler.

---

## Task 3 – The State Transition (Action)

**Endpoint: Cancel an Order**

| Field | Answer |
|---|---|
| HTTP Method | `POST` |
| URI Path | `/orders/{id}/cancel` |
| Success Status Code | **200 OK** (with a body describing the result) |
| One Error Status Code | **404 Not Found** (if the order doesn't exist) |
| Brief Reason | Cancellation is not a simple data-field update — it triggers a chain of side effects (refund, restock, email). A plain `PATCH /orders/{id}` with `{"status": "CANCELLED"}` doesn't capture this intent: it could be misused to set any status. A named sub-resource action (`POST /orders/{id}/cancel`) documents the intent explicitly and lets the service layer enforce business rules. This is the pragmatic "RPC-flavoured" REST pattern used by Stripe, GitHub, and many production APIs. |

### Idempotency Analysis

**Can it be safely called twice without issuing a double-refund?**

Yes — **with correct implementation**. The service layer should:

1. Check the order's current status before processing.
2. If `status == CANCELLED`, return `200 OK` immediately with a message like
   `"Order already cancelled"` — **no further action taken**.
3. If `status == ACTIVE`, execute the cancellation workflow and set `status = CANCELLED`.

This makes the endpoint idempotent: calling it a second time is safe because
the first call already set `status = CANCELLED`, and the guard in step 2 ensures
no duplicate refund is issued.

> **Discussion note for instructors:**  
> Some students will say "`POST` is never idempotent." Clarify that *by default*
> `POST` is not idempotent (the HTTP spec makes no idempotency guarantee for it),
> but a specific `POST` *action* can be designed to be idempotent through
> application-level logic. The distinction between HTTP-level semantics and
> application-level behavior is an important nuance.

---

## Reflection – Suggested Answers

### 1. Why not `DELETE /orders/{id}`?

`DELETE` implies permanent removal of the resource. A cancelled order still exists —
it appears in order history, is auditable, and may need to be referenced for
accounting purposes. Cancellation is a *state transition*, not a deletion.

### 2. State transition vs. data update

A **data update** (PATCH) changes a field value with no business side-effects.
A **state transition** moves a resource from one lifecycle state to another and
may trigger downstream business processes (payments, inventory, notifications).
When side-effects exist, a named action endpoint communicates intent more clearly
and lets the implementation enforce invariants (e.g., can't cancel an already-shipped order).

### 3. `204 No Content` vs `200 OK` for cancellation

`204 No Content` is great when there is genuinely nothing to say (e.g., a DELETE).
For a cancellation, returning `200 OK` with a body (`{ "status": "CANCELLED",
"refundId": "REF-789" }`) is more useful: the caller gets confirmation of
what happened and can display it to the user or log it for auditing.

---

## Complete Contract Summary

| # | Method | URI | Success | Error |
|---|---|---|---|---|
| 1 | `GET` | `/orders/{id}` | 200 OK | 404 Not Found |
| 2 | `GET` | `/orders/{id}/items` | 200 OK | 404 Not Found |
| 3 | `POST` | `/orders/{id}/cancel` | 200 OK | 404 Not Found |

### Additional error codes worth mentioning

| Scenario | Code |
|---|---|
| Order already cancelled | 409 Conflict (alternative design) or 200 (idempotent design) |
| Order cannot be cancelled (already shipped) | 422 Unprocessable Entity |
| Invalid id format (not a number) | 400 Bad Request |
