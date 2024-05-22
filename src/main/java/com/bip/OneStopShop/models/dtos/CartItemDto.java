package com.bip.OneStopShop.models.dtos;


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
}
