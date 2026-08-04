# Demo Walkthrough: Standardized Error Responses (Module 9)

**Focus:** From Stack Traces to RFC 7807 ProblemDetail
**Target Length:** 5 - 7 minutes
**Files:** `ProductController.java`, `GlobalRestExceptionHandler.java`

---

## 0:00 – 1:00 | Introduction & The Problem

*(Screen showing `ProductController.java` open in the IDE)*

"Welcome back. In this demo, we are going to look at standardized error handling in Spring Boot REST APIs.

"Let's look at our `ProductController`. It is deliberately simple. `GET /products/{id}` returns a JSON product object. But if the ID is greater than 100, it throws a `ProductNotFoundException`. And if the ID is exactly 500, it throws a raw `RuntimeException` to simulate a database failure.

"Without any error handling, what does the client see when things go wrong?"

## 1:00 – 2:00 | The Default Error Response

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s http://localhost:8080/products/200 | jq`)*

"Let's hit a missing product. We get Spring's default JSON error response — it has `detail`, `status`, `instance`, and `title`. This is better than a raw stack trace, but it is a non-standard, Spring-specific format. Different frameworks produce different error shapes, which means the client has to write custom parsing logic for every API they talk to.

"The industry solved this problem with RFC 7807 — a standard error format called Problem Detail."

## 2:00 – 3:30 | ProblemDetail and @RestControllerAdvice

*(Switch tabs to `GlobalRestExceptionHandler.java`)*

"Here is our solution: `GlobalRestExceptionHandler`. It is annotated with `@RestControllerAdvice`, which tells Spring that this class handles exceptions across all controllers in the application.

*(Highlight lines 11-14: `handleProductNotFound` method)*

"Our first handler catches `ProductNotFoundException`. We return `ProblemDetail.forStatusAndDetail()`, passing `HttpStatus.NOT_FOUND` and the exception's message. Spring serializes this as a standard RFC 7807 JSON response with `type`, `title`, `status`, and `detail` fields.

*(Highlight lines 16-22: `handleUnexpectedException` method)*

"Our second handler is the catch-all for any `Exception` that does not have a specific handler. We return a 500 Internal Server Error with a generic support message. This is critical — we never want to leak raw stack traces or internal error details to the client."

## 3:30 – 5:00 | Seeing It In Action

*(🖥️ Terminal: `curl -s http://localhost:8080/products/200 | jq`)*

"Let's test the specific handler first. `GET /products/200` now returns a clean RFC 7807 ProblemDetail. We see `status: 404`, `detail: "Product 200 not found"`, and a `title` of 'Not Found'. This is a standardized, machine-readable format that any HTTP client can parse consistently.

*(🖥️ Terminal: `curl -s http://localhost:8080/products/500 | jq`)*

"Now let's trigger the unexpected error. `GET /products/500` returns a 500 with the message 'An unexpected error occurred. Please contact support.' Notice that the actual `RuntimeException` message — 'Simulated database failure' — is hidden. The internal details stay internal.

*(🖥️ Terminal: `curl -s http://localhost:8080/products/1 | jq`)*

"And the happy path still works. `GET /products/1` returns the product JSON with `id`, `name`, and `price` — a clean 200 response. Our error handlers only fire when exceptions are thrown."

## 5:00 – 5:30 | Outro & Summary

"To summarize: `@RestControllerAdvice` centralizes error handling across all controllers. `ProblemDetail` standardizes the error response format per RFC 7807. And a catch-all `Exception` handler ensures no stack trace ever leaks to the client.

"Thanks for watching, and I'll see you in the next module."
