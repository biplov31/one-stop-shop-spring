package com.bip.OneStopShop.services.impl;

import com.bip.OneStopShop.exceptions.OrderNotFoundException;
import com.bip.OneStopShop.models.*;
import com.bip.OneStopShop.models.dtos.*;
import com.bip.OneStopShop.repositories.OrderRepository;
import com.bip.OneStopShop.repositories.ProductRepository;
import com.bip.OneStopShop.repositories.UserRepository;
import com.bip.OneStopShop.services.mappers.OrderMapper;
import com.bip.OneStopShop.services.mappers.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    OrderMapper orderMapper;
    @Mock
    UserMapper userMapper;
    @InjectMocks
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
        userResponseDto = new UserResponseDto(user.getFirstname(), user.getLastname(), user.getEmail());
        userRepository.save(user);

        productOne = new Product("Product 1", "This is Product 1.", 25.0, ProductCategory.MENS_CLOTHING.name());
        productRepository.save(productOne);

        productTwo = new Product("Product 2", "This is Product 2.", 30.0, ProductCategory.MENS_CLOTHING.name());
        productRepository.save(productTwo);

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
        orderDto = new OrderDto(order.getUserId(), orderItemDtos, order.getTotalCost());

        orderItemOneResponseDto = new OrderItemResponseDto(productOne.getTitle(), productOne.getDescription(), productOne.getPrice(), ProductCategory.valueOf(productOne.getCategory()), orderItemOne.getQuantity(), order.getCreatedAt());
        orderItemTwoResponseDto = new OrderItemResponseDto(productTwo.getTitle(), productTwo.getDescription(), productTwo.getPrice(), ProductCategory.valueOf(productTwo.getCategory()), orderItemTwo.getQuantity(), order.getCreatedAt());

        orderItemResponseDtoList.add(orderItemOneResponseDto);
        orderItemResponseDtoList.add(orderItemTwoResponseDto);
        orderListDto = new OrderListDto(userResponseDto, orderItemResponseDtoList, totalCost);
    }

    @Test
    void givenUserId_ShouldFindAllOrders() {
        // mocking all the dependencies
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userMapper.convertUserToUserResponseDto(user)).thenReturn(userResponseDto);
        when(orderRepository.findAllByUserIdOrderByCreatedAtDesc(user.getId())).thenReturn(orderItemResponseDtoList);

        // invoking the method being tested
        OrderListDto userOrderListDto = orderService.findAllOrders(user.getId());

        // verifying outcomes
        assertThat(userOrderListDto).isNotNull();
        assertThat(userOrderListDto.getUserResponseDto().getEmail()).isEqualTo(user.getEmail());
        assertThat(userOrderListDto.getOrderItemResponseDtoList().size()).isEqualTo(orderItemResponseDtoList.size());
        assertThat(userOrderListDto.getCost()).isEqualTo(totalCost);
    }

    @Test
    void givenOrderId_ShouldFindThatOrder() {
        when(orderRepository.findByOrderId(orderItemOne.getId())).thenReturn(orderItemResponseDtoList);

        OrderListDto orderListDto = orderService.findOrderById(order.getId());

        assertThat(orderListDto).isNotNull();
        assertThat(orderListDto.getOrderItemResponseDtoList().size()).isEqualTo(orderItemResponseDtoList.size());
        assertThat(orderListDto.getCost()).isEqualTo(totalCost);
    }

    @Test
    void givenOrderDto_ShouldCreateOrder() {
        when(orderMapper.convertOrderDtoToOrder(orderDto)).thenReturn(order);
        when(orderRepository.findByOrderId(order.getId())).thenReturn(orderItemResponseDtoList);
        when(orderService.findOrderById(order.getId())).thenReturn(orderListDto);

        OrderListDto orderListDto = orderService.placeOrder(orderDto);

        verify(orderRepository).save(order);
    }

    @Test
    void givenOrderId_ShouldDeleteOrderIfFound() {
        when(orderRepository.existsById(order.getId())).thenReturn(true);

        orderService.cancelOrder(order.getId());

        verify(orderRepository).deleteById(order.getId());
    }

    @Test
    void givenOrderIdToDelete_ShouldThrowExceptionIfNotFound() {
        when(orderRepository.existsById(999)).thenReturn(false);

        assertThatThrownBy(() -> orderService.cancelOrder(999))
                .isInstanceOf(OrderNotFoundException.class)
                .hasMessageContaining("Order does not exist.");
    }

}
