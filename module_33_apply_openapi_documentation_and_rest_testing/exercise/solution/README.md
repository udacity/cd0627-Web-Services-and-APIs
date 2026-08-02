# Module 33 - API Documentation and Testing - Solution

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

1. Add the `springdoc-openapi-starter-webmvc-ui` dependency.
2. In `src/main/java/com/ecommerce/docs/OrderController.java`, annotate your controller endpoints with `@Operation` and `@ApiResponses`.


### Key Concepts Demonstrated
- **OpenAPI Specification**
- **Springdoc for auto-generation**
- **Swagger UI**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
