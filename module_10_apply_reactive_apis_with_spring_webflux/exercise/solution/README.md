# Module 10 - Reactive Programming with Spring WebFlux - Solution

## Solution Walkthrough

The solution implements non-blocking streams using WebFlux. The endpoint returns a `Flux` which pushes data to the client asynchronously.

### `DashboardController.java` — The Implementation

```java
@RestController
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

1. In `src/main/java/com/ecommerce/dashboard/DashboardController.java`, change the controller return type to `Flux<Ticker>` to represent a stream of multiple items.
2. Ensure the endpoint produces `text/event-stream` for Server-Sent Events.
3. Use Project Reactor operators to build the non-blocking pipeline.


### Key Concepts Demonstrated
- **Non-blocking event loop architecture**
- **`Flux` and `Mono`**
- **Server-Sent Events (SSE)**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
