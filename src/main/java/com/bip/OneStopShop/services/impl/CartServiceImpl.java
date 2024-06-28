package com.bip.OneStopShop.services.impl;

import com.bip.OneStopShop.exceptions.CartException;
import com.bip.OneStopShop.models.CartItem;
import com.bip.OneStopShop.models.Product;
import com.bip.OneStopShop.models.dtos.CartItemDto;
import com.bip.OneStopShop.models.dtos.CartItemResponseDto;
import com.bip.OneStopShop.models.dtos.CartListDto;
import com.bip.OneStopShop.models.dtos.ProductDto;
import com.bip.OneStopShop.repositories.CartRepository;
import com.bip.OneStopShop.repositories.ProductRepository;
import com.bip.OneStopShop.services.CartService;
import com.bip.OneStopShop.services.mappers.CartItemMapper;
import com.bip.OneStopShop.services.mappers.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CartItemMapper cartItemMapper;

    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository, ProductMapper productMapper, CartItemMapper cartItemMapper) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    public CartListDto findByUserId(Integer id) {
        List<CartItem> cartItems = cartRepository.findByUserIdOrderByCartItemIdDesc(id);
        if (cartItems == null) {
            throw new CartException("User or cart doesn't exist.");
        }

        // N + 1 queries; can be solved with a single SQL join
        List<CartItemResponseDto> cartItemResponseDtos = cartItems.stream().map(item -> {
            Product product = productRepository.findById(item.getProductId()).orElse(null);
            ProductDto productDto = productMapper.convertProductToProductDto(product);

            return cartItemMapper.convertToCartItemResponseDto(item, productDto);
        }).collect(Collectors.toList());

        return new CartListDto(cartItemResponseDtos); // instead of returning List<CartItemResponseDto> this allows us to include total cost
    }

    @Override
    public void addItemToCart(CartItemDto cartItemDto) {
        CartItem existingItem = cartRepository.findByUserIdAndProductId(cartItemDto.getUserId(), cartItemDto.getProductId());
        if (existingItem != null) {
            Integer existingQuantity = existingItem.getQuantity();
            existingItem.setQuantity(existingQuantity + cartItemDto.getQuantity());
            cartRepository.save(existingItem);
        } else {
            cartRepository.save(cartItemMapper.convertToCartItem(cartItemDto));
        }
    }

    @Override
    public void updateCartItem(Integer cartItemId, CartItemDto cartItemDto) {
        if (cartRepository.existsByUserIdAndProductId(cartItemDto.getUserId(), cartItemDto.getProductId())) {
            CartItem cartItemToUpdate = cartItemMapper.convertToCartItem(cartItemDto);
            cartItemToUpdate.setCartItemId(cartItemId);
            cartRepository.save(cartItemToUpdate);
        } else {
            throw new CartException("Product not found in cart.");
        }
    }

    @Override
    public void removeItemFromCart(Integer cartItemId) {
        if (!cartRepository.existsByCartItemId(cartItemId)) {
            throw new CartException("Product not found in cart.");
        }
        cartRepository.deleteByCartItemId(cartItemId);
    }

}
