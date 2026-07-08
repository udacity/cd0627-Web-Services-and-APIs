# Module 10 - Reactive Programming with Spring WebFlux

## Demo Walkthrough

This demo introduces Reactive Spring using WebFlux, shifting from thread-per-request to a non-blocking architecture.

### `ConcurrencyController.java` — Core Implementation

```java
@RestController
public class ConcurrencyController {

    private static final Logger log = LoggerFactory.getLogger(ConcurrencyController.class);
    private final WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com");
    private final RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");

    // Step 1: The WebFlux Event Loop
    @GetMapping("/reactive/todo")
    public Mono<String> getTodoReactive() {
        return webClient.get()
                .uri("/todos/1")
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(todo -> log.info("Running on thread: {}", Thread.currentThread()));
    }
```

### Key Concepts Demonstrated
- **Non-blocking event loop architecture**
- **`Flux` and `Mono`**
- **Server-Sent Events (SSE)**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
