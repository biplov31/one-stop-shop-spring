package com.bip.OneStopShop.models.dtos;


import java.util.Objects;

public class CartItemDto {

    private Integer userId;
    private Integer productId;
    private Integer quantity;

    public CartItemDto(Integer userId, Integer productId, Integer quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    // public CartItemDto(Integer userId, Integer productId) {
    //     this.userId = userId;
    //     this.productId = productId;
    //     this.quantity = 0;
    // }

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

    // without properly implemented equals and hashcode methods, the default implementation in java only cares about the object reference and not the content, and this may cause a problem when testing

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemDto that = (CartItemDto) o;
        return Objects.equals(userId, that.userId) && Objects.equals(productId, that.productId) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId, quantity);
    }
}
