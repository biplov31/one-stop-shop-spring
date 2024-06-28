package com.bip.OneStopShop.services.impl;

import com.bip.OneStopShop.models.OrderItem;
import com.bip.OneStopShop.models.Product;
import com.bip.OneStopShop.models.ProductCategory;
import com.bip.OneStopShop.models.User;
import com.bip.OneStopShop.models.dtos.OrderItemDto;
import com.bip.OneStopShop.models.dtos.OrderItemResponseDto;
import com.bip.OneStopShop.models.dtos.OrderListDto;
import com.bip.OneStopShop.models.dtos.UserResponseDto;
import com.bip.OneStopShop.repositories.OrderRepository;
import com.bip.OneStopShop.repositories.ProductRepository;
import com.bip.OneStopShop.repositories.UserRepository;
import com.bip.OneStopShop.services.mappers.OrderMapper;
import com.bip.OneStopShop.services.mappers.UserMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
    OrderItemDto orderItemTwoDto;
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

        productTwo = new Product("Product 2", "This is Product 2.", 25.0, ProductCategory.MENS_CLOTHING.name());
        productRepository.save(productTwo);

        // orderItemOne = new OrderItem(user.getId(), productOne.getId(), 5);
        // orderItemOneDto = new OrderItemDto(orderItemOne.getUserId(), orderItemOne.getProductId(), orderItemOne.getQuantity(), orderItemOne.getCreatedAt());
        // orderItemTwo = new OrderItem(user.getId(), productTwo.getId(), 10);
        // orderItemTwoDto = new OrderItemDto(orderItemTwo.getUserId(), orderItemTwo.getProductId(), orderItemTwo.getQuantity(), orderItemTwo.getCreatedAt());

        // orderItemOneResponseDto = new OrderItemResponseDto(productOne.getTitle(), productOne.getDescription(), productOne.getPrice(), ProductCategory.valueOf(productOne.getCategory()), orderItemOne.getQuantity(), orderItemOne.getCreatedAt());
        // orderItemTwoResponseDto = new OrderItemResponseDto(productTwo.getTitle(), productTwo.getDescription(), productTwo.getPrice(), ProductCategory.valueOf(productTwo.getCategory()), orderItemTwo.getQuantity(), orderItemTwo.getCreatedAt());

        orderItemResponseDtoList.add(orderItemOneResponseDto);
        orderItemResponseDtoList.add(orderItemTwoResponseDto);
        totalCost = productOne.getPrice() * orderItemOne.getQuantity() + productTwo.getPrice() * orderItemTwo.getQuantity();
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
        // when(orderRepository.findByOrderId(orderItemOne.getId())).thenReturn();
    }

}
