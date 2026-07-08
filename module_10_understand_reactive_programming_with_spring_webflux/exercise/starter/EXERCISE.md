# Module 10 - Reactive Programming with Spring WebFlux - Exercise Instructions

## Exercise Overview

The dashboard API is bottlenecking because it blocks threads while waiting for downstream services. You need to refactor it to a reactive, non-blocking stream using Project Reactor.

---

## Prerequisites
- **Java 21+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

> [!NOTE]
> **Deep Dive:** In Spring WebFlux, returning a `Flux` with `produces = MediaType.TEXT_EVENT_STREAM_VALUE` automatically establishes a Server-Sent Events (SSE) connection. This allows the server to push new data to the client asynchronously over a single persistent HTTP connection without blocking a thread.

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | 1: Implement GET /dashboard/summary-reactive | `src/main/java/com/ecommerce/dashboard/DashboardController.java` |
| 2 | 2: Implement GET /dashboard/ticker | `src/main/java/com/ecommerce/dashboard/DashboardController.java` |


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

- [ ] The endpoint streams data indefinitely.
- [ ] The application operates on a non-blocking event loop.
