# Module 10 - Reactive Spring (WebFlux)

## Demo Walkthrough

This demo introduces Reactive Spring using WebFlux. We shift from the traditional thread-per-request model to a non-blocking, event-loop architecture to handle data streams efficiently.

### `ConcurrencyController.java` — Core Implementation

```java
@GetMapping("/reactive/todo")
    public Mono<String> getTodoReactive() {
        return webClient.get()
                .uri("/todos/1")
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(todo -> log.info("Running on thread: {}", Thread.currentThread()));
    }
```

### Execution Workflow

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `Flux<Ticker>` | Change the controller return type to `Flux<Ticker>`. |
| 2 | `text/event-stream` | Ensure the endpoint produces `text/event-stream`. |
| 3 | `Flux.interval` | Use `Flux.interval` to simulate a reactive stream of data. |


### Expected Output

```
Application started successfully.
Expected API behaviors active.
```

### Key Concepts Demonstrated
- **Non-blocking event loop architecture**
- **`Flux` and `Mono` from Project Reactor**
- **Server-Sent Events (SSE)**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
