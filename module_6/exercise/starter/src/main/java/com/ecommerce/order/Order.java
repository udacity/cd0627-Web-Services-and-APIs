package com.ecommerce.order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class Order {
    private Long id;
    private BigDecimal totalAmount;
    private String status;
    private BigDecimal internalMargin;
    private Instant auditTimestamp;
    private Instant deliveryDate;
    private List<Long> itemIds;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public BigDecimal getInternalMargin() { return internalMargin; }
    public void setInternalMargin(BigDecimal internalMargin) { this.internalMargin = internalMargin; }
    public Instant getAuditTimestamp() { return auditTimestamp; }
    public void setAuditTimestamp(Instant auditTimestamp) { this.auditTimestamp = auditTimestamp; }
    public Instant getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(Instant deliveryDate) { this.deliveryDate = deliveryDate; }
    public List<Long> getItemIds() { return itemIds; }
    public void setItemIds(List<Long> itemIds) { this.itemIds = itemIds; }
}
