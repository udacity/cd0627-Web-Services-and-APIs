# Module 11 — Reactive Programming with Spring WebFlux — Exercise Instructions

## Exercise Overview

The dashboard API is bottlenecking because it blocks threads while waiting for downstream services. You need to refactor it to a reactive, non-blocking approach using Project Reactor.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

1. In `src/main/java/com/ecommerce/dashboard/DashboardController.java`, implement `GET /dashboard/summary-reactive` (Step 1):
   - Rewrite the blocking `getSummaryBlocking()` logic using `WebClient` instead of `RestClient`.
   - Return `Mono<DashboardSummary>` (not a plain object).
   - Use `Mono.zip(productMono, reviewsMono)` to fetch product and reviews **concurrently** — do NOT call `.block()` or chain them sequentially.
   - Inside the `zip` map function, log `Thread.currentThread().toString()` to verify you're on a non-blocking thread.

2. Implement `GET /dashboard/ticker` (Step 2):
   - Return `Flux<Double>` producing simulated stock prices.
   - Produce `MediaType.TEXT_EVENT_STREAM_VALUE` (Server-Sent Events).
   - Use `Flux.interval(Duration.ofMillis(500))` and `.map()` to generate a random `Double` price each tick.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.
> Follow the `// TODO (Step X)` comments in the starter code!

---

## Running the Exercise

```bash
mvn spring-boot:run
```

Test the reactive endpoint:
```bash
curl http://localhost:8080/dashboard/summary-reactive
```

Test the streaming ticker (outputs indefinitely):
```bash
curl http://localhost:8080/dashboard/ticker
```

---

## Success Criteria

- [ ] `GET /dashboard/summary-reactive` returns a `DashboardSummary` without blocking threads.
- [ ] `GET /dashboard/ticker` streams `Double` values indefinitely as Server-Sent Events.
- [ ] Log output confirms aggregation happens on a non-blocking thread (e.g., `reactor-http-nio-*`).
