package com.ecommerce.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    // TODO: 1. Create OrderResponse Record (id, totalAmount, status)
    // TODO: 2. Create CreateOrderRequest Record (totalAmount, status, deliveryDate, itemIds)
    // TODO: 3. Add Validation to CreateOrderRequest:
    //          - totalAmount: @Positive
    //          - status: @NotBlank
    //          - deliveryDate: @FutureOrPresent
    //          - itemIds: @NotEmpty
    // TODO: 4. Create OrderMapper interface using MapStruct.
    //          - Add unmappedTargetPolicy = ReportingPolicy.IGNORE to the @Mapper annotation.
    // TODO: 5. Refactor the endpoints below to use the Records, @Valid, and the Mapper.

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody Order order) {
        order.setId(100L); // Mock save
        return ResponseEntity.created(URI.create("/orders/" + order.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Order order = new Order();
        order.setId(id);
        order.setTotalAmount(new BigDecimal("150.00"));
        order.setStatus("ACTIVE");
        order.setInternalMargin(new BigDecimal("30.00")); // Sensitive!
        order.setAuditTimestamp(Instant.now());          // Sensitive!
        order.setDeliveryDate(Instant.now().plusSeconds(86400));
        order.setItemIds(List.of(1L, 2L));
        return ResponseEntity.ok(order);
    }
}
