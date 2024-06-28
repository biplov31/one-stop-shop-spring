package com.bip.OneStopShop.models.dtos;

import java.util.HashSet;
import java.util.Set;

public class OrderDto {

    private Integer userId;
    private Set<OrderItemDto> orderItemDtos = new HashSet<>();

    public OrderDto() {
    }

    public OrderDto(Integer userId, Set<OrderItemDto> orderItemDtos) {
        this.userId = userId;
        this.orderItemDtos = orderItemDtos;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Set<OrderItemDto> getOrderItemDtos() {
        return orderItemDtos;
    }

    public void setOrderItemDtos(Set<OrderItemDto> orderItemDtos) {
        this.orderItemDtos = orderItemDtos;
    }

}
