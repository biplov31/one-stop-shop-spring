package com.bip.OneStopShop.models.dtos;

// since userId is retrieved from the session, and productId from the product card and quantity is the only field that user can change, this DTO is probably not necessary
public class CartItemUpdateDto {

    private Integer quantity;

    public CartItemUpdateDto(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
