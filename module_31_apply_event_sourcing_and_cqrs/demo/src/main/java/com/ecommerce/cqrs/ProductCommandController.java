package com.ecommerce.cqrs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ProductCommandController {

    private final ProductWriteService writeService;

    public ProductCommandController(ProductWriteService writeService) {
        this.writeService = writeService;
    }

    public record CreateProductRequest(String id, String name, double price) {}

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> createProduct(@RequestBody CreateProductRequest request) {
        writeService.handle(new CreateProductCommand(request.id(), request.name(), request.price()));
        return Map.of("status", "CREATED", "productId", request.id());
    }
}
