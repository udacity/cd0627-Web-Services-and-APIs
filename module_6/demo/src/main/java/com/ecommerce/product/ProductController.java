package com.ecommerce.product;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductMapper mapper;

    public ProductController(ProductMapper mapper) {
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@Valid @RequestBody CreateProductRequest request) {
        Product entity = mapper.toEntity(request);
        entity.setId(1L); // Mock save
        return ResponseEntity.created(URI.create("/products/" + entity.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        Product entity = new Product();
        entity.setId(id);
        entity.setName("Laptop");
        entity.setPrice(new java.math.BigDecimal("999.99"));
        return ResponseEntity.ok(mapper.toResponse(entity));
    }
}
