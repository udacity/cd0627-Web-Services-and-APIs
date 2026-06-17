package com.ecommerce.dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

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

    // Step 2: Server-Sent Events (SSE)
    @GetMapping(value = "/reactive/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamTicks() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(tick -> "Tick: " + tick);
    }

    // Step 3: The Virtual Thread Alternative
    @GetMapping("/blocking/todo")
    public String getTodoBlocking() {
        String todo = restClient.get()
                .uri("/todos/1")
                .retrieve()
                .body(String.class);
        log.info("Blocking call running on thread: {}", Thread.currentThread());
        return todo;
    }
}
