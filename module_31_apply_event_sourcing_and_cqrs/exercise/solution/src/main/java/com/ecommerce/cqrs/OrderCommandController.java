package com.ecommerce.cqrs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderCommandController {

    private final OrderWriteService writeService;

    public OrderCommandController(OrderWriteService writeService) {
        this.writeService = writeService;
    }

    public record PlaceOrderRequest(String orderId, String item, int quantity) {}

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody PlaceOrderRequest request) {
        writeService.handle(new PlaceOrderCommand(request.orderId(), request.item(), request.quantity()));
        return "Order placed: " + request.orderId();
    }

    public record CancelOrderRequest(String orderId, String reason) {}

    @PostMapping("/orders/{orderId}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public String cancelOrder(@PathVariable String orderId, @RequestBody CancelOrderRequest request) {
        writeService.handle(new CancelOrderCommand(orderId, request.reason()));
        return "Order cancelled: " + orderId;
    }
}
