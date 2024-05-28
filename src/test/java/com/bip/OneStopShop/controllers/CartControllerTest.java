package com.bip.OneStopShop.controllers;

import com.bip.OneStopShop.models.CartItem;
import com.bip.OneStopShop.models.Product;
import com.bip.OneStopShop.models.ProductCategory;
import com.bip.OneStopShop.models.User;
import com.bip.OneStopShop.models.dtos.CartItemDto;
import com.bip.OneStopShop.models.dtos.CartItemResponseDto;
import com.bip.OneStopShop.models.dtos.CartListDto;
import com.bip.OneStopShop.models.dtos.ProductDto;
import com.bip.OneStopShop.services.CartService;
import com.bip.OneStopShop.services.impl.CartServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.awt.*;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CartController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@EnableWebMvc
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CartServiceImpl cartService;

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
        cartItem.setCartItemId(1);
        cartItemDto = new CartItemDto(cartItem.getUserId(), cartItem.getProductId(), cartItem.getQuantity());
        cartItemOneResponseDto = new CartItemResponseDto(productDto, cartItem.getQuantity());
    }

    @Test
    void shouldGetCartByUserId() throws Exception {
        CartListDto cartListDto = new CartListDto(List.of(cartItemOneResponseDto), cartItemOneResponseDto.getProductDto().getPrice() * cartItemOneResponseDto.getQuantity());

        when(cartService.findByUserId(user.getId())).thenReturn(cartListDto);

        ResultActions response = mockMvc.perform(get("/cart/" + user.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartItemResponseDtoList.length()", CoreMatchers.is(1)))
                .andExpect(jsonPath("$.cartItemResponseDtoList[0].productDto.title", CoreMatchers.containsString("Product 1")))
                .andExpect(jsonPath("$.cost", CoreMatchers.is(cartItemOneResponseDto.getProductDto().getPrice() * cartItemOneResponseDto.getQuantity())));
    }

    @Test
    void shouldAddToCart() throws Exception {
        ResultActions response = mockMvc.perform(post("/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cartItemDto)));

        response.andExpect(status().isCreated());

        verify(cartService).addItemToCart(any(CartItemDto.class));
    }

    @Test
    void shouldUpdateCart() throws Exception {
        CartItemDto newCartItemDto = new CartItemDto(user.getId(), product.getId(), 10);

        ResultActions response = mockMvc.perform(put("/cart/" + user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newCartItemDto)));

        response.andExpect(status().isNoContent());

        verify(cartService).updateCartItem(user.getId(), newCartItemDto); // works after implementing equals() and hash() in CartItemDto
    }

    @Test
    void shouldDeleteCartByCartItemId() throws Exception {
        ResultActions response = mockMvc.perform(delete("/cart/" + user.getId()))
                .andExpect(status().isNoContent());

        verify(cartService).removeItemFromCart(user.getId());
    }

}
