# Module 14 - Spring Caching

## Demo Walkthrough

In this demo, we implement application-level caching. The approach uses Spring's caching abstraction to store the results of expensive operations in memory.

### `UserController.java` — Core Implementation

```java
@GetMapping("/users/{id}")
    public Map<String, Object> getUser(@PathVariable Long id) {
        // Stub implementation
        return Map.of(
            "id", id,
            "name", "Alice Mock",
            "email", "alice@example.com"
        );
    }
```

### Execution Workflow

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `@EnableCaching` | Add `@EnableCaching` to the main application class. |
| 2 | `@Cacheable("products")` | Annotate the read method with `@Cacheable("products")`. |
| 3 | `@CacheEvict` | Annotate the update/delete methods with `@CacheEvict` to prevent stale data. |


### Expected Output

```
Application started successfully.
Expected API behaviors active.
```

### Key Concepts Demonstrated
- **`@Cacheable` for read-through caching**
- **`@CacheEvict` for cache invalidation**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
