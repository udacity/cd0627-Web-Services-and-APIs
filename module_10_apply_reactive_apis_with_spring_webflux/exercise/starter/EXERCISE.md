# Module 10 - Reactive Programming with Spring WebFlux - Exercise Instructions

## Exercise Overview

The dashboard API is bottlenecking because it blocks threads while waiting for downstream services. You need to refactor it to a reactive, non-blocking stream using Project Reactor.

---

## Prerequisites
- **Java 25+**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

| Step | Task |
|------|-----------|
| 1 | In `src/main/java/com/ecommerce/dashboard/DashboardController.java`, change the controller return type to `Flux<Ticker>` to represent a stream of multiple items. |
| 2 | Ensure the endpoint produces `text/event-stream` for Server-Sent Events. |
| 3 | Use Project Reactor operators to build the non-blocking pipeline. |


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
