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
        READ_MODEL.put(event.orderId(), new OrderView(event.orderId(), event.item(), event.quantity(), "PLACED"));
    }

    @EventListener
    public void onOrderCancelled(OrderCancelledEvent event) {
        OrderView view = READ_MODEL.get(event.orderId());
        if (view != null) {
            view.setStatus("CANCELLED");
        }
    }

    public void rebuildReadModel() {
        READ_MODEL.clear();
        for (OrderEvent event : OrderWriteService.EVENT_STORE) {
            if (event instanceof OrderPlacedEvent pe) {
                onOrderPlaced(pe);
            } else if (event instanceof OrderCancelledEvent ce) {
                onOrderCancelled(ce);
            }
        }
    }

    @GetMapping("/orders")
    public Collection<OrderView> getOrders() {
        return READ_MODEL.values();
    }
}
