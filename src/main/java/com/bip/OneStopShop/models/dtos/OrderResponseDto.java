package com.bip.OneStopShop.models.dtos;

import java.util.HashSet;
import java.util.Set;

public class OrderResponseDto {

    UserResponseDto userResponseDto;
    Set<OrderListDto> orderList = new HashSet<>();

    public OrderResponseDto(UserResponseDto userResponseDto, Set<OrderListDto> orderList) {
        this.userResponseDto = userResponseDto;
        this.orderList = orderList;
    }

    public UserResponseDto getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(UserResponseDto userResponseDto) {
        this.userResponseDto = userResponseDto;
    }

    public Set<OrderListDto> getOrderList() {
        return orderList;
    }

    public void setOrderList(Set<OrderListDto> orderList) {
        this.orderList = orderList;
    }

}
