package com.bip.OneStopShop.models.dtos;

public class OrderItemResponseDto {

    private UserResponseDto userResponseDto;
    private ProductDto productDto;
    private Integer quantity;

    public OrderItemResponseDto() {
    }

    public OrderItemResponseDto(ProductDto productDto, Integer quantity) {
        this.productDto = productDto;
        this.quantity = quantity;
    }

    public OrderItemResponseDto(UserResponseDto userDto, ProductDto productDto, Integer quantity) {
        this.userResponseDto = userDto;
        this.productDto = productDto;
        this.quantity = quantity;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public UserResponseDto getUserDto() {
        return userResponseDto;
    }

    public void setUserDto(UserResponseDto userDto) {
        this.userResponseDto = userDto;
    }
}
