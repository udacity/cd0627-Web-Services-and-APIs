# Module 16 - API Gateway and Service Discovery

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
```bash
mvn clean install
mvn spring-boot:run
```
