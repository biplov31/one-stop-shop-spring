package com.bip.OneStopShop.models.dtos;

import java.util.List;

public class CartListDto {

    private List<CartItemResponseDto> cartItemResponseDtoList;
    private Double cost;

    public CartListDto(List<CartItemResponseDto> cartItemResponseDtoList) {
        this.cartItemResponseDtoList = cartItemResponseDtoList;
        this.cost = findTotalCost(cartItemResponseDtoList);
    }

    public CartListDto(List<CartItemResponseDto> cartItemResponseDtoList, Double cost) {
        this.cartItemResponseDtoList = cartItemResponseDtoList;
        this.cost = cost;
    }

    public static Double findTotalCost(List<CartItemResponseDto> cartItemResponseDtoList) {
        return cartItemResponseDtoList.stream()
                .reduce(0.0, (subtotal, cartItem) -> subtotal + cartItem.getQuantity().doubleValue() * cartItem.getProductDto().getPrice(), Double::sum);
                // without Double::sum, subtotal is of type CartItemResponseDto and hence we can't perform addition
    }

    public List<CartItemResponseDto> getCartItemResponseDtoList() {
        return cartItemResponseDtoList;
    }

    public void setCartItemResponseDtoList(List<CartItemResponseDto> cartItemResponseDtoList) {
        this.cartItemResponseDtoList = cartItemResponseDtoList;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

}
