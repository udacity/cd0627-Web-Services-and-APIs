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

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | 1: Implement GET /orders/{id}. Use ResponseEntity.ok(). Include customer name via customerClient. | `src/main/java/com/ecommerce/order/controller/OrderController.java` |
| 2 | 2: Implement POST /orders. Use ResponseEntity.created() to return 201 Created. | `src/main/java/com/ecommerce/order/controller/OrderController.java` |
| 3 | 3: Implement POST /orders/{id}/cancel. Return 404 if order not found (mock it by checking id > 100), otherwise return 204 No Content. | `src/main/java/com/ecommerce/order/controller/OrderController.java` |
| 4 | 4: Implement GET /orders/{id} for version 2 (e.g. headers="version=2"). Add an "orderSummary" field to the response. | `src/main/java/com/ecommerce/order/controller/OrderController.java` |
| 5 | Configure GetExchange for /internal/customers/{id} | `src/main/java/com/ecommerce/order/client/CustomerClient.java` |


### Key Concepts Demonstrated
- **Spring Boot REST APIs**
- **HTTP Clients**
- **API Versioning**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
