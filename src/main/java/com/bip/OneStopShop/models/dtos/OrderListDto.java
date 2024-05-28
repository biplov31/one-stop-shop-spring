package com.bip.OneStopShop.models.dtos;

import java.util.List;

public class OrderListDto {

    private UserResponseDto userResponseDto;
    private List<OrderItemResponseDto> orderItemResponseDtoList;
    private Double cost;

    public OrderListDto(UserResponseDto userResponseDto, List<OrderItemResponseDto> orderItemResponseDtoList, Double cost) {
        this.userResponseDto = userResponseDto;
        this.orderItemResponseDtoList = orderItemResponseDtoList;
        this.cost = cost;
    }

    public UserResponseDto getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(UserResponseDto userResponseDto) {
        this.userResponseDto = userResponseDto;
    }

    public List<OrderItemResponseDto> getOrderItemResponseDtoList() {
        return orderItemResponseDtoList;
    }

    public void setOrderItemResponseDtoList(List<OrderItemResponseDto> orderItemResponseDtoList) {
        this.orderItemResponseDtoList = orderItemResponseDtoList;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

}
