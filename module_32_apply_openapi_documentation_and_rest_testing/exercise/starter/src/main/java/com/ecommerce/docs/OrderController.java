package com.ecommerce.docs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

// TODO (Step 12): Add @Operation and @ApiResponses on cancelOrder documenting:
//   - 204 No Content (success)
//   - 404 Not Found (order missing)
//   - 422 Unprocessable Entity (invalid state transition)
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return service.createOrder(request);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable String id) {
        return service.getOrder(id);
    }

    @PostMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelOrder(@PathVariable String id) {
        service.cancelOrder(id);
    }
}
