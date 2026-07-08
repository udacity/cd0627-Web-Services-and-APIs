package com.ecommerce.cqrs;

import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class ProductReadService {

    // The Ephemeral Projection
    private final ConcurrentHashMap<String, ProductView> READ_MODEL = new ConcurrentHashMap<>();

    @EventListener
    public void onProductCreated(ProductCreatedEvent event) {
        READ_MODEL.put(event.id(), new ProductView(event.id(), event.name(), event.price()));
    }

    @GetMapping("/products")
    public Collection<ProductView> getProducts() {
        return READ_MODEL.values();
    }
}
