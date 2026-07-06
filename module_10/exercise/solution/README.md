# Module 10 - Reactive Spring (WebFlux) - Solution

## Solution Walkthrough

The solution implements non-blocking streams using Spring WebFlux. The endpoint returns a `Flux` which pushes data to the client asynchronously over a single connection.

### `DashboardController.java` — The Implementation

```java
@RequestMapping("/dashboard")
public class DashboardController {

    private static final Logger log = LoggerFactory.getLogger(DashboardController.class);
    private final WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com");

    @GetMapping("/summary-reactive")
    public Mono<DashboardSummary> getSummaryReactive() {
        Mono<Product> productMono = webClient.get()
                .uri("/posts/1")
                .retrieve()
                .bodyToMono(Product.class);

        Mono<Review[]> reviewsMono = webClient.get()
                .uri("/posts/1/comments")
                .retrieve()
                .bodyToMono(Review[].class);

        // Addition 1: Concurrent Aggregation via Mono.zip
        return Mono.zip(productMono, reviewsMono)
                .map(tuple -> {
                    log.info("Aggregating on thread: {}", Thread.currentThread());
                    return new DashboardSummary(tuple.getT1(), tuple.getT2());
                });
    }
```

### Step-by-step Design Decisions:

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `Flux<Ticker>` | Change the controller return type to `Flux<Ticker>`. |
| 2 | `text/event-stream` | Ensure the endpoint produces `text/event-stream`. |
| 3 | `Flux.interval` | Use `Flux.interval` to simulate a reactive stream of data. |


### Expected Output

```
══════════════════════════════════════════
 Integration Successful 
══════════════════════════════════════════
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
