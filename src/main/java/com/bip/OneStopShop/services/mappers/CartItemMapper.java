package com.bip.OneStopShop.services.mappers;

import com.bip.OneStopShop.models.CartItem;
import com.bip.OneStopShop.models.dtos.CartItemDto;
import com.bip.OneStopShop.models.dtos.CartItemResponseDto;
import com.bip.OneStopShop.models.dtos.ProductDto;
import org.springframework.stereotype.Service;

@Service
public class CartItemMapper {

    public CartItemResponseDto convertToCartItemResponseDto(CartItem cartItem, ProductDto productDto) {
        CartItemResponseDto cartItemResponseDto = new CartItemResponseDto();
        cartItemResponseDto.setProductDto(productDto);
        cartItemResponseDto.setQuantity(cartItem.getQuantity());

        return cartItemResponseDto;
    }

    public CartItem convertToCartItem(CartItemDto cartItemDto) {
        return new CartItem(cartItemDto.getUserId(), cartItemDto.getProductId(), cartItemDto.getQuantity());
    }

}
