package com.ecommerce.cqrs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class OrderCommandController {

    private final OrderWriteService writeService;

    public OrderCommandController(OrderWriteService writeService) {
        this.writeService = writeService;
    }

    public record PlaceOrderRequest(String orderId, String item, int quantity) {}

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> placeOrder(@RequestBody PlaceOrderRequest request) {
        writeService.handle(new PlaceOrderCommand(request.orderId(), request.item(), request.quantity()));
        return Map.of("status", "CREATED", "orderId", request.orderId());
    }

    public record CancelOrderRequest(String orderId, String reason) {}

    @PostMapping("/orders/{orderId}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> cancelOrder(@PathVariable String orderId, @RequestBody CancelOrderRequest request) {
        writeService.handle(new CancelOrderCommand(orderId, request.reason()));
        return Map.of("status", "CANCELLED", "orderId", orderId);
    }
}

