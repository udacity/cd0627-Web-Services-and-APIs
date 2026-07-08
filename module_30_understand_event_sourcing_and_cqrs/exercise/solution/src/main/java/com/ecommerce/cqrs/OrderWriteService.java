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
        OrderPlacedEvent event = new OrderPlacedEvent(cmd.orderId(), cmd.item(), cmd.quantity());
        EVENT_STORE.add(event);
        publisher.publishEvent(event);
    }

    public void handle(CancelOrderCommand cmd) {
        OrderCancelledEvent event = new OrderCancelledEvent(cmd.orderId(), cmd.reason());
        EVENT_STORE.add(event);
        publisher.publishEvent(event);
    }
}
