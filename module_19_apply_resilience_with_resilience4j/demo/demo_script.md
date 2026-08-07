# Demo Walkthrough: Circuit Breaker and Resilience Patterns (Module 19)

**Focus:** When Downstream Services Fail — Retry, Circuit Breaker, and Graceful Degradation
**Target Length:** 5 - 7 minutes
**Files:** `InventoryController.java`, `InventoryClient.java`

---

## 0:00 – 1:00 | Introduction & The Problem

*(Screen showing `InventoryController.java` open in the IDE)*

"Welcome back. In this demo, we are going to look at Resilience Patterns with Resilience4j.

"Here is the problem: our API calls a downstream Inventory service. When that service is healthy, everything works. But what happens when it is slow, overloaded, or completely down? Without any resilience patterns, our API blocks indefinitely, consumes threads, and eventually crashes. One failing service takes down the entire system — this is called a cascading failure.

"We are going to see three patterns that prevent this: Retry, Circuit Breaker, and Fallback."

## 1:00 – 2:30 | The Setup

*(Switch tabs to `InventoryClient.java`)*

"Our `InventoryClient` calls a downstream service. It is annotated with Resilience4j's `@CircuitBreaker` and `@Retry`. When the `fail` parameter is true, the downstream service throws an exception to simulate a failure.

"The Resilience4j annotations wrap the method call in a defensive shell. Let's look at the configuration to understand what each pattern does."

*(Switch tabs to `application.properties`)*

"In the configuration file, we have the retry config — 3 max attempts with exponential backoff. This means if the first call fails, Resilience4j waits and tries again, up to 3 times. The backoff multiplier increases the wait time between retries.

"Below that is the circuit breaker. It uses a sliding window of 5 calls. If 50% or more of those calls fail, the circuit trips to 'OPEN' state and stops making calls entirely for 10 seconds. After that, it enters 'HALF-OPEN' and tests with one request to see if the service has recovered."

## 2:30 – 4:00 | Seeing It In Action

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s "http://localhost:8080/api/inventory?fail=false" | jq`)*

"Let's see it in action. With `fail=false`, the call succeeds normally.

*(Run multiple times with `fail=true`: `curl -s "http://localhost:8080/api/inventory?fail=true"`)*

"Now let's simulate failures. With `fail=true`, the first call fails, retries kick in — you can see the retry attempts in the logs — and after 3 attempts, the fallback triggers.

"If we keep sending failing requests, the circuit breaker tracks the failure rate. Once it crosses the 50% threshold over the sliding window, the circuit trips to OPEN.

*(Show the logs with circuit breaker state transitions)*

"Look at the logs: 'CircuitBreaker payment transitioned from CLOSED to OPEN.' Now all subsequent calls immediately hit the fallback — the downstream service is not called at all. The circuit breaker is protecting the failing service from being overwhelmed."

## 4:00 – 5:00 | The Fallback

"The fallback method is the graceful degradation strategy. Instead of crashing with a 500 error, we return a friendly 'PENDING' response. The user sees 'We are experiencing delays. Your order will be processed shortly.' This is infinitely better than a stack trace.

"After the wait duration expires, the circuit breaker enters HALF-OPEN and allows one test request through. If it succeeds, the circuit closes and normal operation resumes."

## 5:00 – 5:30 | Outro & Summary

"To summarize: Retry handles transient failures with exponential backoff. The Circuit Breaker detects sustained failures and stops calling the broken service. The Fallback provides graceful degradation instead of crashing. Together, these three patterns prevent cascading failures in your microservices architecture.

"Thanks for watching, and I'll see you in the next module."
