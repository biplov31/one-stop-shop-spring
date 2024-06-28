package com.bip.OneStopShop.models.dtos;

import java.time.LocalDateTime;

public class OrderItemDto {

    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private LocalDateTime createdAt;

    public OrderItemDto() {
    }

    public OrderItemDto(Integer userId, Integer productId, Integer quantity, LocalDateTime createdAt) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
