package com.ecommerce.order.controller;

import com.ecommerce.order.client.CustomerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CustomerClient customerClient;

    public OrderController(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    // TODO (Step 1) 1: Implement GET /orders/{id}. Use ResponseEntity.ok(). Include customer name via customerClient.
    
    // TODO (Step 2) 2: Implement POST /orders. Use ResponseEntity.created() to return 201 Created.

    // TODO (Step 3) 3: Implement POST /orders/{id}/cancel. Return 404 if order not found (mock it by checking id > 100), otherwise return 204 No Content.

    // TODO (Step 4) 4: Implement GET /orders/{id} for version 2 (e.g. headers="version=2"). Add an "orderSummary" field to the response.
}
