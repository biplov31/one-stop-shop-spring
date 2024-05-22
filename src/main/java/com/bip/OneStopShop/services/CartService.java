package com.bip.OneStopShop.services;

import com.bip.OneStopShop.models.CartItem;
import com.bip.OneStopShop.models.dtos.CartItemDto;
import com.bip.OneStopShop.models.dtos.CartItemResponseDto;
import com.bip.OneStopShop.models.dtos.CartListDto;

import java.util.List;

public interface CartService {

    CartListDto findByUserId(Integer id);

    void addItemToCart(CartItemDto cartItemDto);

    void updateCartItem(Integer cartItemId, CartItemDto cartItemDto);

    void removeItemFromCart(Integer cartItemId);

}
