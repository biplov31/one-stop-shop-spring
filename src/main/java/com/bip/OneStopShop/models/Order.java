package com.bip.OneStopShop.models;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table("user_order")
public class Order {

    @Id
    private Integer id;
    @NotNull
    private Integer userId;
    @MappedCollection(idColumn = "order_id")
    private Set<OrderItem> orderItems = new HashSet<>();
    private Double totalCost;
    private LocalDateTime createdAt;

    public Order() {
        this.createdAt = LocalDateTime.now();
    }

    public Order(Integer userId, Set<OrderItem> orderItems, Double totalCost) {
        this.userId = userId;
        orderItems.forEach(this::addOrderItem);
        this.totalCost = totalCost;
        this.createdAt = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(@NotNull Integer userId) {
        this.userId = userId;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.orderId = this;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> items) {
        items.forEach(orderItem -> this.addOrderItem(orderItem));
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double cost) {
        this.totalCost = cost;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
