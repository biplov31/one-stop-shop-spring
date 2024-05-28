package com.bip.OneStopShop.services.impl;

import com.bip.OneStopShop.exceptions.CartException;
import com.bip.OneStopShop.models.CartItem;
import com.bip.OneStopShop.models.Product;
import com.bip.OneStopShop.models.ProductCategory;
import com.bip.OneStopShop.models.User;
import com.bip.OneStopShop.models.dtos.CartItemDto;
import com.bip.OneStopShop.models.dtos.CartItemResponseDto;
import com.bip.OneStopShop.models.dtos.CartListDto;
import com.bip.OneStopShop.models.dtos.ProductDto;
import com.bip.OneStopShop.repositories.CartRepository;
import com.bip.OneStopShop.repositories.ProductRepository;
import com.bip.OneStopShop.repositories.UserRepository;
import com.bip.OneStopShop.services.mappers.CartItemMapper;
import com.bip.OneStopShop.services.mappers.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    CartRepository cartRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    ProductMapper productMapper;
    @Mock
    CartItemMapper cartItemMapper;
    @InjectMocks
    CartServiceImpl cartService;

    private User user;
    private Product product;
    private ProductDto productDto;
    private CartItem cartItem;
    private CartItemDto cartItemDto;
    private CartListDto cartListDto;
    private CartItemResponseDto cartItemOneResponseDto;

    @BeforeEach
    void init() {
        user = new User("John", "Doe", "john123", "john@gmail.com");
        user.setId(1);

        product = new Product("Product 1", "This is Product 1.", 25.0, ProductCategory.MENS_CLOTHING.name());
        product.setId(1);
        productDto = new ProductDto(product.getTitle(), product.getDescription(), product.getPrice(), product.getCategory());

        cartItem = new CartItem(user.getId(), product.getId(), 4);
        cartItemDto = new CartItemDto(cartItem.getUserId(), cartItem.getProductId(), cartItem.getQuantity());
        cartItemOneResponseDto = new CartItemResponseDto(productDto, cartItem.getQuantity());
    }

    @Test
    void givenUserId_ShouldReturnCartListDto() {
        when(cartRepository.findByUserIdOrderByCartItemIdDesc(user.getId())).thenReturn(List.of(cartItem));
        when(productRepository.findById(cartItem.getProductId())).thenReturn(Optional.of(product));
        when(productMapper.convertProductToProductDto(product)).thenReturn(productDto);
        when(cartItemMapper.convertToCartItemResponseDto(cartItem, productDto)).thenReturn(cartItemOneResponseDto);

        CartListDto result = cartService.findByUserId(user.getId());

        assertThat(result).isNotNull();
        assertThat(result.getCartItemResponseDtoList()).hasSize(1);
        assertThat(result.getCartItemResponseDtoList().get(0).getProductDto()).isEqualTo(productDto);

        verify(cartRepository).findByUserIdOrderByCartItemIdDesc(user.getId());
        verify(productRepository).findById(cartItem.getProductId());
    }

    @Test
    void givenUserId_ShouldThrowException_IfCartNotFound() {
        User unknownUser = new User("Unknown", "Unknown", "xyz123", "xyz@gmail.com");
        unknownUser.setId(999);

        when(cartRepository.findByUserIdOrderByCartItemIdDesc(unknownUser.getId())).thenReturn(null);

        assertThatThrownBy(() -> cartService.findByUserId(unknownUser.getId()))
                .isInstanceOf(CartException.class)
                .hasMessageContaining("User or cart doesn't exist.");
    }

    @Test
    void givenCartItemDto_ShouldAddItemToUserCart() {
        CartItemDto cartItemDto = new CartItemDto(1, 1, 3);
        CartItem existingCartItem = new CartItem(1, 1, 3);

        when(cartRepository.findByUserIdAndProductId(cartItemDto.getUserId(), cartItemDto.getProductId())).thenReturn(existingCartItem);

        cartService.addItemToCart(cartItemDto);

        assertThat(existingCartItem.getQuantity()).isEqualTo(6);
        verify(cartRepository).save(existingCartItem);
    }

    @Test
    void givenCartItemIdAndCartItemDto_ShouldUpdateCartItem() {
        CartItem newCartItem = new CartItem(cartItem.getUserId(), cartItem.getProductId(), 0);
        newCartItem.setCartItemId(cartItem.getCartItemId());
        CartItemDto newCartItemDto = new CartItemDto(newCartItem.getUserId(), newCartItem.getProductId(), newCartItem.getQuantity());

        when(cartRepository.existsByUserIdAndProductId(cartItemDto.getUserId(), cartItem.getProductId())).thenReturn(true);
        when(cartItemMapper.convertToCartItem(newCartItemDto)).thenReturn(newCartItem);
        when(cartRepository.save(newCartItem)).thenReturn(newCartItem);

        cartService.updateCartItem(cartItem.getCartItemId(), newCartItemDto);

        verify(cartRepository).save(newCartItem);

        assertThat(newCartItem.getQuantity()).isEqualTo(0);
    }

    @Test
    void givenCartItemIdAndCartItemDto_ShouldThrowExceptionIfNotFound() {
        CartItem unknownItem = new CartItem(999, 999, 10);
        CartItemDto unknownItemDto = new CartItemDto(unknownItem.getUserId(), unknownItem.getProductId(), unknownItem.getQuantity());

        when(cartRepository.existsByUserIdAndProductId(unknownItem.getUserId(), unknownItem.getProductId())).thenReturn(false);

        // the arguments used in the 'when' stubbing and the actual method call should be consistent i.e. the userId and productId should match those used in the existsByUserIdAndProductId method.
        assertThatThrownBy(() -> cartService.updateCartItem(unknownItem.getCartItemId(), unknownItemDto))
                .isInstanceOf(CartException.class)
                .hasMessageContaining("Product not found in cart.");
    }

    @Test
    void shouldRemoveCartItemFromCartByCartItemId() {
        when(cartRepository.existsByCartItemId(cartItem.getCartItemId())).thenReturn(true);

        cartService.removeItemFromCart(cartItem.getCartItemId());

        verify(cartRepository).deleteByCartItemId(cartItem.getCartItemId());
    }

    @Test
    void givenCartItemId_ShouldThrowException_IfCartItemToRemoveNotFound() {
        CartItem unknownItem = new CartItem(999, 999, 999);

        when(cartRepository.existsByCartItemId(unknownItem.getCartItemId())).thenReturn(false);

        assertThatThrownBy(() -> cartService.removeItemFromCart(unknownItem.getCartItemId()))
                .isInstanceOf(CartException.class)
                .hasMessageContaining("Product not found in cart.");
    }

}