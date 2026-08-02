# Solution Walkthrough: Resilience4j (Module 19)

**Focus:** Configuring Retry, Circuit Breaker, and the Fallback Method
**Target Length:** 5 - 7 minutes
**Files:** `application.yml`, `OrderController.java`

---

## 0:00 – 1:00 | Introduction & The Exercise

*(Screen showing `OrderController.java` open in the IDE)*

"Welcome back. In this video, we're going to walk through the solution to the Resilience4j exercise.

"Our goal was to protect a checkout endpoint from a flaky downstream payment service. We needed to configure retry with exponential backoff, a circuit breaker, and implement a fallback method.

"Let's walk through each step."

## 1:00 – 2:30 | Step 1: Retry Configuration

*(Switch tabs to `application.yml`)*

"Step 1 is the retry configuration. Under `resilience4j.retry.instances.payment`, we set `max-attempts: 3`. This means Resilience4j will try the call up to 3 times before giving up.

"We also configure exponential backoff with `wait-duration: 500ms` and `multiplier: 2`. The first retry waits 500 milliseconds, the second waits 1 second, and the third waits 2 seconds. This gives the downstream service time to recover between attempts."

## 2:30 – 3:30 | Step 2: Circuit Breaker Configuration

*(Highlight the circuit breaker section in `application.yml`)*

"Step 2 is the circuit breaker configuration. Under `resilience4j.circuitbreaker.instances.payment`, we set `sliding-window-size: 5` and `failure-rate-threshold: 50`. This means the circuit breaker tracks the last 5 calls, and if 3 or more fail — 60% — the circuit trips open.

"Once open, `wait-duration-in-open-state: 10s` means no calls are made for 10 seconds. After that, it enters half-open mode and tests with one request.

"The `ignoreExceptions` list includes `InvalidCreditCardException`. This is a business error — a bad credit card number is not a transient failure, so we do not want it to count toward the circuit breaker's failure rate."

## 3:30 – 4:30 | Step 3: The Fallback Method

*(Switch tabs to `OrderController.java`, highlight lines 32-38: `paymentFallback` method)*

"Step 3 is the fallback method. Look at `paymentFallback`. It takes the same parameters as the original method — `String type` — plus a `Throwable t` parameter that carries the exception.

"When the circuit is open or retries are exhausted, this method is called instead. It returns a 'PENDING' status with a user-friendly message: 'We are experiencing delays. Your order is secure and will be processed shortly.'

"This is graceful degradation — the user sees a helpful message instead of a 500 error."

## 4:30 – 6:00 | Running & Verification

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s http://localhost:8080/api/checkout | jq`)*

"Let's test. First, a successful checkout — we get the payment confirmation.

*(Run multiple times: `curl -s http://localhost:8080/api/checkout | jq`)*

"Now let's keep calling. The payment service fails randomly. When it fails, we see in the logs: 'Retry attempt 1', 'Retry attempt 2', and on the third failure, the fallback triggers — 'PENDING, will process asynchronously.'

"After enough failures accumulate in the sliding window, the circuit breaker trips open. Now every call immediately hits the fallback — no retries, no downstream calls at all. The failing service is completely protected.

"After 10 seconds, the circuit breaker enters half-open, tests one request, and if it succeeds, closes the circuit and resumes normal operation."

## 6:00 – 6:30 | Outro

"To summarize: We configured retry with exponential backoff for transient failures, a circuit breaker to detect sustained outages, and a fallback method for graceful degradation. The key insight is that `ignoreExceptions` prevents business errors from tripping the circuit — only infrastructure failures trigger the resilience patterns.

"Great job if you got this working. I'll see you in the next module."
