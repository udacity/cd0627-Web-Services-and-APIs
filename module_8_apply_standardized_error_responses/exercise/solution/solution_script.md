# Solution Walkthrough: Standardized Error Responses (Module 8)

**Focus:** Four Exception Handlers for a Complete Error Strategy
**Target Length:** 5 - 7 minutes
**File:** `GlobalRestExceptionHandler.java`

---

## 0:00 – 1:00 | Introduction & The Exercise

*(Screen showing `GlobalRestExceptionHandler.java` open in the IDE)*

"Welcome back. In this video, we're going to walk through the solution to the Standardized Error Responses exercise.

"Our goal was to implement four exception handlers in a single `@RestControllerAdvice` class, each returning an RFC 7807 `ProblemDetail` response. We needed to handle: unexpected generic exceptions, order not found, invalid order state, and Bean Validation failures.

"Let's walk through each one."

## 1:00 – 2:00 | Step 1: The Catch-All Handler

*(Highlight lines 15-21: `handleUnexpectedException` method)*

"Step 1 is the generic catch-all. This `@ExceptionHandler(Exception.class)` catches any exception that does not match a more specific handler. We return a 500 Internal Server Error with a static support message.

"This is our safety net. If something unexpected happens deep inside the application — a NullPointerException, a database timeout — the client sees a clean, generic message instead of a stack trace."

## 2:00 – 3:00 | Step 2: OrderNotFoundException → 404

*(Highlight lines 23-26: `handleOrderNotFound` method)*

"Step 2 handles `OrderNotFoundException`. We return `HttpStatus.NOT_FOUND` — a 404 — with the exception's message as the detail.

"When the client requests an order that doesn't exist, they get a structured ProblemDetail with `status: 404` and `detail: 'Order 42 not found'`. Clean, specific, and standardized."

## 3:00 – 4:00 | Step 3: InvalidOrderStateException → 422

*(Highlight lines 28-31: `handleInvalidOrderState` method)*

"Step 3 handles `InvalidOrderStateException`. The status here is `HttpStatus.UNPROCESSABLE_ENTITY` — a 422.

"This is the right status when the request is syntactically valid but semantically wrong. For example, trying to cancel an order that has already been shipped. The request body is fine, but the business logic cannot process it."

## 4:00 – 5:30 | Step 4: Validation Errors → 400

*(Highlight lines 33-40: `handleValidationException` method)*

"Step 4 is the most involved: `MethodArgumentNotValidException`. This is thrown automatically by Spring when a `@Valid` annotated request body fails Bean Validation.

"The exception contains a `BindingResult` with all the field-level errors. We stream through `getFieldErrors()`, map each one to a readable string like 'totalAmount: must be positive', and join them with semicolons.

"The result is a single `ProblemDetail` response with `status: 400` and a `detail` field that lists every validation failure in one clean string. The client can parse and display these to the user."

## 5:30 – 6:30 | Running & Verification

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s http://localhost:8080/orders/999 | jq`)*

"Let's verify. First, `GET /orders/999` — we get a 404 ProblemDetail with 'Order 999 not found'.

*(🖥️ Terminal: `curl -s -X POST http://localhost:8080/orders/1/cancel | jq`)*

"Cancelling a shipped order gives us a 422 — 'Cannot cancel a shipped order'.

*(🖥️ Terminal: `curl -s -X POST http://localhost:8080/orders -H "Content-Type: application/json" -d '{"totalAmount":-5,"status":""}' | jq`)*

"An invalid create request gives us a 400 with specific field errors: 'totalAmount: must be greater than 0; status: must not be blank'.

"All four handlers are working. Every error path returns a clean, standardized RFC 7807 response."

## 6:30 – 7:00 | Outro

"To summarize: We built a complete error handling strategy with four handlers — from the generic safety net to specific business exceptions to validation failures. Every response is a standardized `ProblemDetail`, and no internal details ever leak to the client.

"Great job if you got this working. I'll see you in the next module."
