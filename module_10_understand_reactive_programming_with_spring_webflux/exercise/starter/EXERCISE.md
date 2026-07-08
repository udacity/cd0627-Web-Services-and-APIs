# Module 10 - Reactive Spring (WebFlux) - Exercise Instructions

## Exercise Overview

The dashboard API is bottlenecking because it blocks threads while waiting for downstream services. You need to refactor it to a reactive, non-blocking stream using Project Reactor.

---

## Prerequisites
- **Java 25**
- **Maven 3.9+**

---

## Step-by-Step Implementation Guide

### Step 1
Change the controller return type to `Flux<Ticker>`.

### Step 2
Ensure the endpoint produces `text/event-stream`.

### Step 3
Use `Flux.interval` to simulate a reactive stream of data.

> [!IMPORTANT]
> Ensure you compile frequently and check for syntax errors as you build out the implementation.

---

## Running the Exercise

```bash
mvn spring-boot:run
```

---

## Success Criteria

- [ ] The endpoint streams data indefinitely.
- [ ] The application does not exhaust Tomcat worker threads.
