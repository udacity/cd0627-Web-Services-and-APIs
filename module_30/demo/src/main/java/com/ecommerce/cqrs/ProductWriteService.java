package com.ecommerce.cqrs;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

@Service
public class ProductWriteService {

    // The Ultimate Source of Truth
    public static final List<Object> EVENT_STORE = new CopyOnWriteArrayList<>();
    
    private final ApplicationEventPublisher publisher;

    public ProductWriteService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void handle(CreateProductCommand cmd) {
        ProductCreatedEvent event = new ProductCreatedEvent(cmd.id(), cmd.name(), cmd.price(), System.currentTimeMillis());
        
        // Append-only log
        EVENT_STORE.add(event);
        
        // Broadcast to Read Models
        publisher.publishEvent(event);
    }
}
