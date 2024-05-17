package com.bip.OneStopShop.models.dtos;

import com.bip.OneStopShop.models.Review;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductDto {

    private String title;
    private String description;
    private Double price;
    private String category;
    // private Set<Review> reviews = new HashSet<>();
    private Review review;

    public ProductDto() {
    }

    public ProductDto(String title, String description, Double price, String category, Review review) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.review = review;
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

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", review=" + review +
                '}';
    }
}
