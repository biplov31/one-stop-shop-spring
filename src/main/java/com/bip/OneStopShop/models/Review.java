package com.bip.OneStopShop.models;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDate;

public class Review {

    @NotBlank
    private String content;
    private LocalDate createdOn;
    private LocalDate updatedOn;
    // private AggregateReference<Product, Integer> productId;
    private AggregateReference<User, Integer> userId;
    @Transient
    Product product;

    public Review(String content, AggregateReference<User, Integer> userId) {
        this.content = content;
        this.userId = userId;
        this.createdOn = LocalDate.now();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AggregateReference<User, Integer> getUserId() {
        return userId;
    }

    public void setUserId(AggregateReference<User, Integer> userId) {
        this.userId = userId;
    }
}
