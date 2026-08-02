# Module 7 - Request/Response Handling and DTOs

## Demo Walkthrough

This demo illustrates how to cleanly separate external API contracts from internal domain models using DTOs and auto-generated mappers.

### `ProductMapperImpl.java` — Core Implementation

```java
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponse toResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        BigDecimal price = null;

        id = product.getId();
        name = product.getName();
        price = product.getPrice();

        ProductResponse productResponse = new ProductResponse( id, name, price );

        return productResponse;
    }

    @Override
    public Product toEntity(CreateProductRequest request) {
        if ( request == null ) {
            return null;
    // ...
}
```

### Key Concepts Demonstrated
- **Data Transfer Objects (DTOs)**
- **MapStruct for object mapping**
- **Layer isolation**

## How to Run
```bash
mvn clean install
mvn spring-boot:run
```
