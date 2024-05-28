package com.bip.OneStopShop.models.dtos;

import java.time.LocalDate;

public class OrderItemDto {

    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private LocalDate createdOn;

    public OrderItemDto() {
    }

    public OrderItemDto(Integer userId, Integer productId, Integer quantity, LocalDate createdOn) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.createdOn = createdOn;
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

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }
}
