package com.ecommerce.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Value("${server.port}")
    private String port;

    @GetMapping
    public Map<String, Object> getOrders(
            @RequestHeader(value = "X-Correlation-ID", required = false) String correlationId) {
        
        log.info("Processing order request. Correlation ID: {}, Served from port: {}", correlationId, port);
        
        return Map.of(
            "status", "SUCCESS",
            "message", "Orders fetched",
            "servedByPort", port,
            "correlationId", correlationId != null ? correlationId : "NONE"
        );
    }
}
