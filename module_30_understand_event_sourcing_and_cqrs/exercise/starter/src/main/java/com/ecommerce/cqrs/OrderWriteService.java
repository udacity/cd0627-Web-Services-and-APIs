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
        // TODO (Step 6): Instantiate an OrderPlacedEvent
        // TODO (Step 7): Append to EVENT_STORE
        // TODO (Step 8): Publish event
    }

    public void handle(CancelOrderCommand cmd) {
        // TODO (Step 9): Instantiate an OrderCancelledEvent
        // TODO (Step 10): Append to EVENT_STORE
        // TODO (Step 11): Publish event
    }
}
