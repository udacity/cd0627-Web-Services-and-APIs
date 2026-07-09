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

1. Configure the `eureka-server` to act as the service registry.
2. Configure `api-gateway` routes using Spring Cloud Gateway.
3. Implement a `CorrelationIdFilter` in the gateway to attach tracking headers to all requests.


### Key Concepts Demonstrated
- **Eureka Service Registry**
- **Spring Cloud Gateway**
- **Global Filters**

## How to Run

To demonstrate Service Discovery and Load Balancing, you must run multiple applications in separate terminal windows:

1. **Start Eureka Server**:
   ```bash
   cd eureka-server
   mvn spring-boot:run
   ```
2. **Start API Gateway**:
   ```bash
   cd api-gateway
   mvn spring-boot:run
   ```
3. **Start Order Service (Instance 1 on port 8081)**:
   ```bash
   cd order-service
   SERVER_PORT=8081 mvn spring-boot:run
   ```
4. **Start Order Service (Instance 2 on port 8082)**:
   ```bash
   cd order-service
   SERVER_PORT=8082 mvn spring-boot:run
   ```

**Test Load Balancing**:
Hit the gateway multiple times and observe the logs in the two `order-service` terminals to see the requests distributed round-robin!
```bash
curl http://localhost:8080/api/orders
```