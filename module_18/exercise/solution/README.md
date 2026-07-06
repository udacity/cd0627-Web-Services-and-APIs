# Module 18 - Pagination and Sorting - Solution

## Solution Walkthrough

The solution implements efficient data retrieval. Spring automatically maps query parameters to the `Pageable` object, which the repository uses to execute optimized paginated queries.

### `OrderController.java` — The Implementation

```java
@GetMapping("/api/checkout")
    public Map<String, Object> checkout(@RequestParam(defaultValue = "VALID") String type) {
        return paymentClient.processPayment(type);
    }
```

### Step-by-step Design Decisions:

| Step | Operation | Purpose |
|------|-----------|----------------|
| 1 | `Pageable` | Update the repository method to accept a `Pageable` argument. |
| 2 | `Pageable` | Modify the controller to accept a `Pageable` parameter. |
| 3 | `Page<Order>` | Return a `Page<Order>` instead of a `List<Order>`. |


### Expected Output

```
══════════════════════════════════════════
 Integration Successful 
══════════════════════════════════════════
```

### Key Concepts Demonstrated
- **Spring Data `Pageable`**
- **Automatic query translation for `LIMIT` and `OFFSET`**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
