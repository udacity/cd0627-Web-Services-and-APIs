package com.ecommerce.order;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface UserClient {

    record UserResponse(Long id, String name, String email) {}

    @GetExchange("/users/{id}")
    UserResponse getUser(@PathVariable("id") Long id);
}
