package com.ecommerce.docs;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String id;
    private String itemIds;
    private String status;

    public Order() {}

    public Order(String id, String itemIds, String status) {
        this.id = id;
        this.itemIds = itemIds;
        this.status = status;
    }

    public String getId() { return id; }
    public String getItemIds() { return itemIds; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
