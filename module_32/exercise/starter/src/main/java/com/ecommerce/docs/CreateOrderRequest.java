package com.ecommerce.docs;

import java.util.List;

public record CreateOrderRequest(List<String> itemIds) {}
