# Module 4 - Building REST APIs with Spring Boot - Solution

## Solution Walkthrough

The solution implements standard Spring Web MVC controllers, utilizing annotations to map requests and an HTTP interface client to cleanly talk to downstream APIs.

### `InternalCustomerController.java` — The Implementation

```java
@RestController
public class InternalCustomerController {
    @GetMapping("/internal/customers/{id}")
    public String getCustomerName(@PathVariable long id) {
        return "John Doe";
    }
```

### Step-by-step Design Decisions:

1. In `src/main/java/com/ecommerce/order/controller/OrderController.java`, annotate the class with `@RestController` and `@RequestMapping` to handle web requests.
2. Implement a GET mapping to retrieve a customer by ID.
3. Implement a POST mapping to create a new customer, returning a 201 Created status.


### Key Concepts Demonstrated
- **Spring Boot REST APIs**
- **HTTP Clients**
- **API Versioning**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
