package com.bip.OneStopShop.controllers;

import com.bip.OneStopShop.models.Cart;
import com.bip.OneStopShop.models.CartItem;
import com.bip.OneStopShop.models.dtos.CartItemDto;
import com.bip.OneStopShop.models.dtos.CartItemResponseDto;
import com.bip.OneStopShop.models.dtos.CartListDto;
import com.bip.OneStopShop.services.impl.CartServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CartListDto> getCart(@PathVariable Integer userId) {
        return new ResponseEntity<>(cartService.findByUserId(userId), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addToCart(@RequestBody CartItemDto cartItemDto) {
        cartService.addItemToCart(cartItemDto);
    }

    @PutMapping("/{cartItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCart(@PathVariable Integer cartItemId, @RequestBody CartItemDto cartItemDto) {
        cartService.updateCartItem(cartItemId, cartItemDto);
    }

    @DeleteMapping("/{cartItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFromCart(@PathVariable Integer cartItemId) {
        cartService.removeItemFromCart(cartItemId);
    }

}
