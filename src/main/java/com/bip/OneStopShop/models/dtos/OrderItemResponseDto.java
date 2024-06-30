package com.bip.OneStopShop.models.dtos;

import com.bip.OneStopShop.models.ProductCategory;

import java.time.LocalDateTime;

public class OrderItemResponseDto {

    private String title;
    private String description;
    private Double price;
    private ProductCategory category;
    private Integer quantity;
    private LocalDateTime createdAt;

    public OrderItemResponseDto() {
    }

    public OrderItemResponseDto(String title, String description, Double price, ProductCategory category, Integer quantity, LocalDateTime createdAt) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
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
