package com.ecommerce.order;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderMapper mapper;

    public OrderController(OrderMapper mapper) {
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        Order order = mapper.toEntity(request);
        order.setId(100L); // Mock save
        return ResponseEntity.created(URI.create("/orders/" + order.getId())).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        Order order = new Order();
        order.setId(id);
        order.setTotalAmount(new BigDecimal("150.00"));
        order.setStatus("ACTIVE");
        order.setInternalMargin(new BigDecimal("30.00")); // Sensitive! (now hidden by mapping)
        order.setAuditTimestamp(Instant.now());          // Sensitive! (now hidden by mapping)
        order.setDeliveryDate(Instant.now().plusSeconds(86400));
        order.setItemIds(List.of(1L, 2L));
        
        return ResponseEntity.ok(mapper.toResponse(order));
    }
}
