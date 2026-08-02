# Solution Walkthrough: Reactive APIs with Spring WebFlux (Module 11)

**Focus:** Concurrent Aggregation with Mono.zip and Live Streaming with Flux
**Target Length:** 5 - 7 minutes
**File:** `DashboardController.java`

---

## 0:00 – 1:00 | Introduction & The Exercise

*(Screen showing `DashboardController.java` open in the IDE)*

"Welcome back. In this video, we're going to walk through the solution to the Reactive APIs exercise.

"Our goal was to refactor a blocking dashboard endpoint into a reactive, non-blocking one. The dashboard needs to fetch two pieces of data — a product and its reviews — from a downstream service. In the blocking version, these calls happen sequentially. We needed to make them concurrent using `Mono.zip()`, and then implement a streaming ticker endpoint using `Flux`.

"Let's walk through each step."

## 1:00 – 3:00 | Step 1: Concurrent Aggregation with Mono.zip

*(Highlight lines 22-40: `getSummaryReactive()` method)*

"Step 1 is the concurrent aggregation endpoint: `GET /dashboard/summary-reactive`. The return type is `Mono<DashboardSummary>` — an asynchronous value that resolves when both data sources are ready.

*(Highlight lines 23-33: the two Mono declarations)*

"We start by creating two independent `Mono` calls. The first uses `WebClient` to fetch a product from `/posts/1`, deserializing it into a `Product` object. The second fetches the reviews from `/posts/1/comments`, deserializing into a `Review` array.

"At this point, neither call has executed yet. They are just blueprints.

*(Highlight lines 36-40: `Mono.zip()`)*

"The key line is `Mono.zip(productMono, reviewsMono)`. This tells Reactor: 'subscribe to both Monos simultaneously, and when both have completed, combine their results.' Inside the `.map()`, we extract the two values using `tuple.getT1()` and `tuple.getT2()` and construct our `DashboardSummary`.

"Notice we also log the current thread. This will prove that the aggregation runs on a non-blocking event loop thread — not a servlet thread."

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s http://localhost:8080/dashboard/summary-reactive | jq`)*

"Let's test it. We get a complete `DashboardSummary` with the product data and review array. And looking at the server logs, the thread is `reactor-http-nio` — confirming we are fully non-blocking. Both calls ran concurrently, cutting the response time roughly in half compared to sequential blocking calls."

## 3:00 – 4:30 | Step 2: Live Streaming with Flux

*(Highlight lines 43-47: `streamPrices()` method)*

"Step 2 is the streaming ticker endpoint: `GET /dashboard/ticker`.

"The return type is `Flux<Double>` — a reactive stream that can emit many values over time. The `produces` attribute is set to `MediaType.TEXT_EVENT_STREAM_VALUE`, which enables Server-Sent Events. The client opens a single HTTP connection, and the server pushes values as they become available.

"The implementation uses `Flux.interval(Duration.ofMillis(500))` to emit a tick every 500 milliseconds. Each tick is mapped to a simulated stock price: `100.0 + (Math.random() * 10)`.

"This is a powerful pattern for real-time dashboards, live analytics, or notification feeds."

*(🖥️ Terminal: `curl -N http://localhost:8080/dashboard/ticker`)*

"Let's test it. We see price values streaming in every half second: 107.23, 102.15, 109.87... The connection stays open and events keep flowing. This is real-time data without polling.

*(Press Ctrl+C to stop)*

"Press Ctrl+C to stop the stream."

## 4:30 – 5:00 | Outro

"To summarize: We used `Mono.zip()` to fetch two data sources concurrently without blocking any threads, and `Flux.interval()` to stream real-time data as Server-Sent Events. These are the two core reactive patterns — concurrent aggregation and live streaming.

"Great job if you got this working. I'll see you in the next module."
