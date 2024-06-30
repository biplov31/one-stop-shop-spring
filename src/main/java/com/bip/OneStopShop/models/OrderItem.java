package com.bip.OneStopShop.models;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Table("order_item")
public class OrderItem {

    @Id
    private Integer id;
    @Transient // we need to make the back references @Transient so that Spring Data JDBC doesn't try to persist them leading to infinite loops
    Order orderId;
    @NotNull
    private Integer productId;
    @NotNull
    private Double price; // price is subject to change (inflation, discounts), so it makes sense to store it to maintain a purchase history
    @NotNull
    private Integer quantity;
    // private Integer cartItemId;

    public OrderItem() {
    }

    public OrderItem(Integer productId, Double price, Integer quantity) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull Integer getProductId() {
        return productId;
    }

    public void setProductId(@NotNull Integer productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(@NotNull Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull Integer quantity) {
        this.quantity = quantity;
    }

}
