package com.bip.OneStopShop.models.dtos;

public class CartItemResponseDto {

    // private UserDto userDto; // only needed in Order for checkout form
    private ProductDto productDto;
    private Integer quantity;

    public CartItemResponseDto() {
    }

    public CartItemResponseDto(ProductDto productDto, Integer quantity) {
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

}
