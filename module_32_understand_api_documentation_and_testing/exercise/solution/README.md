# Module 32 - API Documentation and Testing - Solution

## Solution Walkthrough

The solution achieves zero-friction API documentation by decorating the controller with `@Operation` and `@ApiResponses`.

### `Order.java` — The Implementation

```java
public class Order {
    @Id
    private String id;
    private String itemIds;
    private String status;

    public Order() {}

    public Order(String id, String itemIds, String status) {
        this.id = id;
        this.itemIds = itemIds;
        this.status = status;
    }

    public String getId() { return id; }
    public String getItemIds() { return itemIds; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
```

### Step-by-step Design Decisions:

| Step | Task | Target File |
|------|-----------|----------------|
| 1 | Implement the integration test | `src/test/java/com/ecommerce/docs/OrderIntegrationTest.java` |
| 2 | POST /orders to create, then GET /orders/{id} to retrieve and assert persistence | `src/test/java/com/ecommerce/docs/OrderIntegrationTest.java` |
| 3 | POST an order and capture the returned id | `src/test/java/com/ecommerce/docs/OrderIntegrationTest.java` |
| 4 | GET /orders/{id} and assert status 200 and body fields | `src/test/java/com/ecommerce/docs/OrderIntegrationTest.java` |
| 5 | Write a WebMvcTest for invalid payload | `src/test/java/com/ecommerce/docs/OrderValidationTest.java` |
| 6 | Assert HTTP 400 and RFC 7807 problem detail fields | `src/test/java/com/ecommerce/docs/OrderValidationTest.java` |
| 7 | Submit POST /orders with {"itemIds":[]} | `src/test/java/com/ecommerce/docs/OrderValidationTest.java` |
| 8 | Assert status is 400 Bad Request | `src/test/java/com/ecommerce/docs/OrderValidationTest.java` |
| 9 | Assert jsonPath("$.type") exists | `src/test/java/com/ecommerce/docs/OrderValidationTest.java` |
| 10 | Assert jsonPath("$.title").value("Bad Request") | `src/test/java/com/ecommerce/docs/OrderValidationTest.java` |
| 11 | Assert jsonPath("$.detail") contains "empty" | `src/test/java/com/ecommerce/docs/OrderValidationTest.java` |
| 12 | Add `@Operation` and `@ApiResponses` on cancelOrder documenting: | `src/main/java/com/ecommerce/docs/OrderController.java` |


### Key Concepts Demonstrated
- **OpenAPI Specification**
- **Springdoc for auto-generation**
- **Swagger UI**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
