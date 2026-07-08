package com.ecommerce.cqrs;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

@Service
public class OrderWriteService {

    public static final List<OrderEvent> EVENT_STORE = new CopyOnWriteArrayList<>();
    
    private final ApplicationEventPublisher publisher;

    public OrderWriteService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void handle(PlaceOrderCommand cmd) {
        // TODO: Instantiate an OrderPlacedEvent
        // TODO: Append to EVENT_STORE
        // TODO: Publish event
    }

    public void handle(CancelOrderCommand cmd) {
        // TODO: Instantiate an OrderCancelledEvent
        // TODO: Append to EVENT_STORE
        // TODO: Publish event
    }
}
