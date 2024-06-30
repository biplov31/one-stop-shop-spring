package com.bip.OneStopShop.controllers;

import com.bip.OneStopShop.models.*;
import com.bip.OneStopShop.models.dtos.*;
import com.bip.OneStopShop.services.impl.OrderServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.C;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@EnableWebMvc
public class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    OrderServiceImpl orderService;

    User user;
    UserResponseDto userResponseDto;
    Product productOne;
    Product productTwo;
    OrderItem orderItemOne;
    OrderItemDto orderItemOneDto;
    OrderItemResponseDto orderItemOneResponseDto;
    OrderItem orderItemTwo;
    Set<OrderItem> orderItems = new HashSet<>();
    OrderItemDto orderItemTwoDto;
    Set<OrderItemDto> orderItemDtos = new HashSet<>();
    Order order;
    OrderDto orderDto;
    OrderItemResponseDto orderItemTwoResponseDto;
    List<OrderItemResponseDto> orderItemResponseDtoList = new ArrayList<>();
    Double totalCost;
    OrderListDto orderListDto;

    @BeforeEach
    void init() {
        user = new User("John", "Doe", "john123", "john@gmail.com");
        user.setId(1);
        userResponseDto = new UserResponseDto(user.getFirstname(), user.getLastname(), user.getEmail());

        productOne = new Product("Product 1", "This is Product 1.", 25.0, ProductCategory.MENS_CLOTHING.name());
        productOne.setId(1);

        productTwo = new Product("Product 2", "This is Product 2.", 30.0, ProductCategory.MENS_CLOTHING.name());
        productTwo.setId(2);

        orderItemOne = new OrderItem(productOne.getId(), productOne.getPrice(), 10);
        orderItems.add(orderItemOne);
        orderItemOneDto = new OrderItemDto(orderItemOne.getProductId(), orderItemOne.getPrice(), orderItemOne.getQuantity());
        orderItemDtos.add(orderItemOneDto);
        orderItemTwo = new OrderItem(productTwo.getId(), productTwo.getPrice(), 5);
        orderItems.add(orderItemTwo);
        orderItemTwoDto = new OrderItemDto(orderItemTwo.getProductId(), orderItemTwo.getPrice(), orderItemTwo.getQuantity());
        orderItemDtos.add(orderItemTwoDto);

        totalCost = orderItemOneDto.getQuantity() * orderItemOneDto.getPrice() + orderItemTwoDto.getQuantity() * orderItemTwoDto.getPrice();

        order = new Order(user.getId(), orderItems, totalCost);
        order.setId(1);
        orderDto = new OrderDto(order.getUserId(), orderItemDtos, order.getTotalCost());

        orderItemOneResponseDto = new OrderItemResponseDto(productOne.getTitle(), productOne.getDescription(), productOne.getPrice(), ProductCategory.valueOf(productOne.getCategory()), orderItemOne.getQuantity(), order.getCreatedAt());
        orderItemTwoResponseDto = new OrderItemResponseDto(productTwo.getTitle(), productTwo.getDescription(), productTwo.getPrice(), ProductCategory.valueOf(productTwo.getCategory()), orderItemTwo.getQuantity(), order.getCreatedAt());

        orderItemResponseDtoList.add(orderItemOneResponseDto);
        orderItemResponseDtoList.add(orderItemTwoResponseDto);
        orderListDto = new OrderListDto(userResponseDto, orderItemResponseDtoList, totalCost);
    }

    @Test
    void shouldGetAllOrdersByUserId() throws Exception {
        when(orderService.findAllOrders(user.getId())).thenReturn(orderListDto);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/order/user/" + user.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userResponseDto.email", CoreMatchers.containsString(user.getEmail())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderItemResponseDtoList.length()", CoreMatchers.is(2)));
    }

    @Test
    void shouldGetOrderById() throws Exception {
        when(orderService.findOrderById(order.getId())).thenReturn(orderListDto);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/order/" + order.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.orderItemResponseDtoList.length()", CoreMatchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cost", CoreMatchers.is(totalCost)));
    }

    @Test
    void shouldCreateOrder() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
        verify(orderService).placeOrder(any(OrderDto.class));
    }

    @Test
    void shouldDeleteOrder() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.delete("/order/" + order.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(orderService).cancelOrder(order.getId());
    }

}
