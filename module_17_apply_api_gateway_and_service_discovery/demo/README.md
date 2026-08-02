# Module 17 - API Gateway and Service Discovery

## Demo Walkthrough

This demo sets up the foundational infrastructure for microservices routing using Spring Cloud Gateway and Netflix Eureka.

### `LoggingGlobalFilter.java` — Core Implementation

```java
public class LoggingGlobalFilter implements GlobalFilter {

    private static final Logger log = LoggerFactory.getLogger(LoggingGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Incoming request path: {}", exchange.getRequest().getPath());
        return chain.filter(exchange);
    }
}
```

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