package com.ecommerce.cqrs;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderSaga {

    private final OrderWriteService writeService;

    public OrderSaga(OrderWriteService writeService) {
        this.writeService = writeService;
    }

    @EventListener
    public void onPaymentFailed(PaymentFailedEvent event) {
        // TODO (Step 5): Issue a CancelOrderCommand to the write service
    }
}
