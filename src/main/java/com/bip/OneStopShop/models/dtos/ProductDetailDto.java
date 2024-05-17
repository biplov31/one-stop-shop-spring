package com.bip.OneStopShop.models.dtos;

import com.bip.OneStopShop.models.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProductDetailDto {
    private String title;
    private String description;
    private Double price;
    private String category;
    private Map<String, String> reviews = new LinkedHashMap<>();

    public ProductDetailDto() {
    }

    public ProductDetailDto(Product product, Map<String, String> reviews) {
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.reviews = reviews;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Map<String, String> getReviews() {
        return reviews;
    }

    public void setReviews(Map<String, String> reviews) {
        this.reviews = reviews;
    }
}
