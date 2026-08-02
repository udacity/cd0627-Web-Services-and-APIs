package com.ecommerce.product.dto;

import java.math.BigDecimal;

/**
 * Request body for a partial product update (PATCH /products/{id}).
 *
 * <p>All fields are optional ({@code null} means "do not change").
 * This mirrors the HTTP PATCH semantics: the client only sends
 * the fields it wants to modify.
 *
 * <p>Example – update price only:
 * <pre>{@code
 * {
 *   "price": 129.99
 * }
 * }</pre>
 *
 * <p>Because Java records have no concept of "optional" fields
 * out of the box, we use a plain class with nullable fields here.
 */
public class PatchProductRequest {

    private String name;
    private String description;
    private BigDecimal price;

    // Default constructor required for Jackson deserialization
    public PatchProductRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
