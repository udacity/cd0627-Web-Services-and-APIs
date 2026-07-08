package com.ecommerce.docs;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public record CreateOrderRequest(
    @Schema(example = "[\"item-abc\", \"item-xyz\"]", description = "List of item IDs to include in the order")
    List<String> itemIds
) {}
