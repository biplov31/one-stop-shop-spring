package com.bip.OneStopShop.models.dtos;

import java.util.HashSet;
import java.util.Set;

public class OrderDto {

    private Integer userId;
    private Set<OrderItemDto> orderItems = new HashSet<>();
    private Double totalCost;

    public OrderDto() {
    }

    public OrderDto(Integer userId, Set<OrderItemDto> orderItems, Double totalCost) {
        this.userId = userId;
        this.orderItems = orderItems;
        this.totalCost = totalCost;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Set<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

}
