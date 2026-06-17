package com.ecommerce.dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private static final Logger log = LoggerFactory.getLogger(DashboardController.class);
    private final RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
    private final WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com");

    @GetMapping("/summary-blocking")
    public DashboardSummary getSummaryBlocking() {
        log.info("Starting blocking fetches on thread: {}", Thread.currentThread());
        
        // Sequential, blocking calls
        Product product = restClient.get().uri("/posts/1").retrieve().body(Product.class);
        Review[] reviews = restClient.get().uri("/posts/1/comments").retrieve().body(Review[].class);
        
        return new DashboardSummary(product, reviews);
    }

    // TODO 1: Implement GET /dashboard/summary-reactive
    // Rewrite using WebClient and return Mono<DashboardSummary>.
    // DO NOT use block(). DO NOT chain them sequentially.
    // Must use Mono.zip(productMono, reviewsMono) to fetch concurrently.
    // Inside the zip map function, log the Thread.currentThread().toString() to verify execution context.

    // TODO 2: Implement GET /dashboard/ticker
    // Return Flux<Double> producing simulated prices.
    // Produce MediaType.TEXT_EVENT_STREAM_VALUE.
    // Use Flux.interval(Duration.ofMillis(500)) and map to a random Double.
}
