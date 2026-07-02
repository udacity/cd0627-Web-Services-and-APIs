package com.ecommerce.docs;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
    public ProductRecord findById(String id) {
        return new ProductRecord(id, "Test Product");
    }
}
