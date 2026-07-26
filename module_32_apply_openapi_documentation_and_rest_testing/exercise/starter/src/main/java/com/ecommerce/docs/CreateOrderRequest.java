package com.ecommerce.docs;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record CreateOrderRequest(@NotEmpty List<String> itemIds) {}
