# Demo Walkthrough: Reactive APIs with Spring WebFlux (Module 11)

**Focus:** The Blocking Bottleneck vs. The Reactive Event Loop
**Target Length:** 5 - 7 minutes
**File:** `ConcurrencyController.java`

---

## 0:00 – 1:00 | Introduction & Scenario

*(Screen showing `ConcurrencyController.java` open in the IDE)*

"Welcome back. In this demo, we are going to look at Reactive Programming with Spring WebFlux.

"Our scenario today involves a common backend problem: calling external APIs. We have a controller that fetches data from a downstream service. We are going to see the difference between a blocking call — which ties up a thread — and a reactive call — which frees the thread to handle other requests while waiting for the response."

## 1:00 – 2:30 | Step 1: The Reactive Event Loop

*(Highlight lines 23-30: `getTodoReactive()` method)*

"Let's start with the reactive approach. `GET /reactive/todo` returns a `Mono<String>`. A `Mono` is Project Reactor's type for an asynchronous value that arrives at some point in the future — similar to a `CompletableFuture`, but with a much richer set of operators.

"We use `WebClient` — Spring WebFlux's non-blocking HTTP client — to call an external API. The call is assembled declaratively: `.get()`, `.uri()`, `.retrieve()`, `.bodyToMono()`. No thread is blocked while waiting for the response.

"Notice the `.doOnNext()` operator. We log the current thread to prove the code is running on a non-blocking Netty event loop thread, not a Tomcat servlet thread.

*(🖥️ Terminal: `mvn spring-boot:run`)*

*(🖥️ Terminal: `curl -s http://localhost:8080/reactive/todo | jq`)*

"Let's test it. We get the response from the downstream API. And looking at the server logs, the thread is `reactor-http-nio-2` — a Netty event loop thread. No servlet thread was ever blocked."

## 2:30 – 3:45 | Step 2: Server-Sent Events with Flux

*(Highlight lines 33-37: `streamTicks()` method)*

"Step 2 introduces `Flux` — the reactive type for a stream of values. While `Mono` emits at most one value, `Flux` can emit many.

"Our `GET /reactive/stream` endpoint produces `MediaType.TEXT_EVENT_STREAM_VALUE` — this is the Server-Sent Events protocol. We use `Flux.interval()` to emit a tick every second, and `.map()` to transform each tick into a string.

"The connection stays open, and the server pushes values to the client as they become available — no polling required.

*(🖥️ Terminal: `curl -N http://localhost:8080/reactive/stream`)*

"Let's test it. We see events streaming in one per second: Tick 0, Tick 1, Tick 2. This is real-time data streaming with zero polling overhead."

*(Press Ctrl+C to stop the stream)*

## 3:45 – 5:00 | Step 3: The Blocking Alternative

*(Highlight lines 40-48: `getTodoBlocking()` method)*

"For comparison, let's look at the blocking alternative. `GET /blocking/todo` uses `RestClient` — the synchronous HTTP client. It calls `.body(String.class)`, which blocks the thread until the response arrives.

*(🖥️ Terminal: `curl -s http://localhost:8080/blocking/todo | jq`)*

"Looking at the server logs, the thread is now `tomcat-handler-1` — a servlet thread. While this thread was blocked waiting for the downstream API, it could not handle any other requests.

"Both approaches produce the same result. But under high concurrency — thousands of simultaneous requests — the reactive version scales dramatically better because it does not consume a thread per request."

## 5:00 – 5:30 | Outro & Summary

"To summarize: Use `WebClient` with `Mono` for non-blocking single-value calls. Use `Flux` with Server-Sent Events for real-time streaming. And understand that the reactive model scales better under high concurrency because it frees threads while waiting for I/O.

"Thanks for watching, and I'll see you in the next module."
