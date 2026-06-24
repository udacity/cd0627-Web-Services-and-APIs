package com.ecommerce.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    // TODO: Inject server.port to prove load balancing

    @GetMapping
    public Map<String, Object> getOrders() {
        // TODO: Log incoming X-Correlation-ID header if present
        
        return Map.of(
            "status", "SUCCESS",
            "message", "Orders fetched"
            // TODO: Include port in response to see round-robin via gateway
        );
    }
}
