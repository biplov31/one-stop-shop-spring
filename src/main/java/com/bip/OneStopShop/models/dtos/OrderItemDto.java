package com.bip.OneStopShop.models.dtos;

public class OrderItemDto {

    private Integer productId;
    private Double price;
    private Integer quantity;

    public OrderItemDto() {
    }

    public OrderItemDto(Integer productId, Double price, Integer quantity) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
