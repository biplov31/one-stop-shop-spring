package com.bip.OneStopShop.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.HashSet;
import java.util.Set;

public class Product {

    @Id
    private Integer id;
    @NotBlank
    private String title;
    private String description;
    @NotNull
    private Double price;
    private String category;
    private Set<Review> reviews = new HashSet<>();

    public Product() {
        this.id = null;
    }

    public Product(String title, String description, Double price, String category, Set<Review> reviews) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        reviews.forEach(this::addReview);
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.product = this;
    }

    public Set<Review> getReviews() {
        return this.reviews;
    }

    public void setReviews(Set<Review> reviews) {
        reviews.forEach(this::addReview);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", reviews=" + reviews +
                '}';
    }
}
