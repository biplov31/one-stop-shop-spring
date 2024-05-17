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

    public Review() {
        this.createdOn = LocalDate.now();
    }

    public Review(String content, AggregateReference<User, Integer> userId) {
        this.content = content;
        // AggregateReference<User, Integer> userRef = AggregateReference.to(userId);
        this.userId = userId;
        this.createdOn = LocalDate.now();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDate date) {
        this.updatedOn = date;
    }

    public AggregateReference<User, Integer> getUserId() {
        return userId;
    }

    // causes an error: com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `org.springframework.data.jdbc.core.mapping.AggregateReference`
    // public void setUserId(AggregateReference<User, Integer> userId) {
    //     this.userId = userId;
    // }

    public void setUserId(int userId) {
        AggregateReference<User, Integer> userRef = AggregateReference.to(userId);
        this.userId = userRef;
    }

    // throws error likely due to the AggregateReference
    @Override
    public String toString() {
        return "Review{" +
                "content='" + content + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", userId=" + userId.getId().toString() +
                ", product=" + product +
                '}';
    }
}
