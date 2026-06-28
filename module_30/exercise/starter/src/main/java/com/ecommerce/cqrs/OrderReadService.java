package com.ecommerce.cqrs;

import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class OrderReadService {

    private final ConcurrentHashMap<String, OrderView> READ_MODEL = new ConcurrentHashMap<>();

    @EventListener
    public void onOrderPlaced(OrderPlacedEvent event) {
        // TODO: Put a new OrderView into READ_MODEL with status "PLACED"
    }

    @EventListener
    public void onOrderCancelled(OrderCancelledEvent event) {
        // TODO: Update the OrderView status to "CANCELLED"
    }

    public void rebuildReadModel() {
        READ_MODEL.clear();
        // TODO: Iterate over OrderWriteService.EVENT_STORE
        // TODO: For each event, if OrderPlacedEvent put new view, if OrderCancelledEvent update status.
    }

    @GetMapping("/orders")
    public Collection<OrderView> getOrders() {
        return READ_MODEL.values();
    }
}
