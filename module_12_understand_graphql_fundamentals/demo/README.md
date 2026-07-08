# Module 12 - GraphQL Fundamentals

## Demo Walkthrough

This demo explores building APIs with Spring for GraphQL. We shift from rigid REST endpoints to flexible, client-driven queries.

### `ProductController.java` — Core Implementation

```java
@QueryMapping
    public List<Product> products() {
        return products;
    }
```

### Key Concepts Demonstrated
- **GraphQL Schemas**
- **`@QueryMapping` and `@SchemaMapping`**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
