package com.ecommerce.cqrs;

public class OrderView {
    private String orderId;
    private String item;
    private int quantity;
    private String status;

    public OrderView(String orderId, String item, int quantity, String status) {
        this.orderId = orderId;
        this.item = item;
        this.quantity = quantity;
        this.status = status;
    }

    public String getOrderId() { return orderId; }
    public String getItem() { return item; }
    public int getQuantity() { return quantity; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
