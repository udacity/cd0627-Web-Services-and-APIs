package com.ecommerce.dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

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

    // Addition 2: Live Data Streaming
    @GetMapping(value = "/ticker", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Double> streamPrices() {
        return Flux.interval(Duration.ofMillis(500))
                .map(tick -> 100.0 + (Math.random() * 10)); // Simulated price
    }
}
