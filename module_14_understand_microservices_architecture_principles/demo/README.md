# Module 14 - Microservices Architecture Principles

## Demo Walkthrough

This demo demonstrates the principles of microservices architecture by splitting a monolithic application into discrete services.

### `UserController.java` — Core Implementation

```java
@RestController
public class UserController {

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

### Key Concepts Demonstrated
- **Microservices Decomposition**
- **Inter-service communication**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
