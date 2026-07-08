# Module 16 - Testing Slices

## Demo Walkthrough

This demo explores the different slices of testing in Spring Boot. We cover unit testing, web layer testing to isolate the controller, and data layer testing.

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

### Execution Workflow

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `@WebMvcTest` | Write a `@WebMvcTest` for the controller, mocking the service layer. |
| 2 | `MockMvc` | Use `MockMvc` to perform a GET request and assert the JSON path. |
| 3 | `@DataJpaTest` | Write a `@DataJpaTest` to verify custom repository queries against an embedded database. |


### Expected Output

```
Application started successfully.
Expected API behaviors active.
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
