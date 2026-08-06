package com.ecommerce.order;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {

    private final UserClient userClient;

    public OrderController(UserClient userClient) {
        this.userClient = userClient;
    }

    @PostMapping("/orders")
    public Map<String, Object> placeOrder(@RequestParam Long userId) {
        // Network Bridge: Synchronous REST call to User Service
        UserClient.UserResponse user = userClient.getUser(userId);

        return Map.of(
            "message", "Order placed successfully",
            "customerName", user.name(),
            "customerEmail", user.email()
        );
    }
}
