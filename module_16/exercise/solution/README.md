# Module 16 - Testing Slices - Solution

## Solution Walkthrough

The solution provides robust test coverage. The `@WebMvcTest` isolates the web layer, while the `@DataJpaTest` verifies the repository logic without loading the full application context.

### `CorrelationIdFilter.java` — The Implementation

```java
public class CorrelationIdFilter implements GlobalFilter {

    private static final Logger log = LoggerFactory.getLogger(CorrelationIdFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String correlationId = UUID.randomUUID().toString();
        log.info("Generated Correlation ID: {} for path: {}", correlationId, exchange.getRequest().getPath());

        // Mutate the request to add the header
        ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                .header("X-Correlation-ID", correlationId)
                .build();

        ServerWebExchange modifiedExchange = exchange.mutate()
                .request(modifiedRequest)
                .build();

        return chain.filter(modifiedExchange);
    }
    // ...
}
```

### Step-by-step Design Decisions:

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `@WebMvcTest` | Write a `@WebMvcTest` for the controller, mocking the service layer. |
| 2 | `MockMvc` | Use `MockMvc` to perform a GET request and assert the JSON path. |
| 3 | `@DataJpaTest` | Write a `@DataJpaTest` to verify custom repository queries against an embedded database. |


### Expected Output

```
══════════════════════════════════════════
 Integration Successful 
══════════════════════════════════════════
```

### Key Concepts Demonstrated
- **`@WebMvcTest` for web layer isolation**
- **`MockMvc` for HTTP assertions**
- **`@DataJpaTest` for database testing**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
