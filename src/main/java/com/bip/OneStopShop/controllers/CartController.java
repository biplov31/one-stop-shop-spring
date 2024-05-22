package com.bip.OneStopShop.controllers;

import com.bip.OneStopShop.models.Cart;
import com.bip.OneStopShop.models.CartItem;
import com.bip.OneStopShop.models.dtos.CartItemDto;
import com.bip.OneStopShop.models.dtos.CartItemResponseDto;
import com.bip.OneStopShop.models.dtos.CartListDto;
import com.bip.OneStopShop.services.impl.CartServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartServiceImpl cartService;

    public CartController(CartServiceImpl cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public CartListDto getCart(@PathVariable Integer userId) {
        return cartService.findByUserId(userId);
    }

    @PostMapping
    public void addToCart(@RequestBody CartItemDto cartItemDto) {
        cartService.addItemToCart(cartItemDto);
    }

    @PutMapping("/{cartItemId}")
    public void updateCart(@PathVariable Integer cartItemId, @RequestBody CartItemDto cartItemDto) {
        cartService.updateCartItem(cartItemId, cartItemDto);
    }

    @DeleteMapping("/{cartItemId}")
    public void deleteFromCart(@PathVariable Integer cartItemId) {
        cartService.removeItemFromCart(cartItemId);
    }

}
