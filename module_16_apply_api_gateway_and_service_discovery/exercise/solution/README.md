# Module 16 - API Gateway and Service Discovery - Solution

## Solution Walkthrough

The solution implements dynamic routing. The Gateway acts as the single entrypoint, querying Eureka to find healthy service instances and routing the request appropriately.

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
}
```

### Step-by-step Design Decisions:

| Step | Task |
|------|-----------|
| 1 | Configure the `eureka-server` to act as the service registry. |
| 2 | Configure `api-gateway` routes using Spring Cloud Gateway. |
| 3 | Implement a `CorrelationIdFilter` in the gateway to attach tracking headers to all requests. |


### Key Concepts Demonstrated
- **Eureka Service Registry**
- **Spring Cloud Gateway**
- **Global Filters**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
