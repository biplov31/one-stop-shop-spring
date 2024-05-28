package com.bip.OneStopShop.services.impl;

import com.bip.OneStopShop.exceptions.OrderNotFoundException;
import com.bip.OneStopShop.exceptions.ProductNotFoundException;
import com.bip.OneStopShop.exceptions.UserNotFoundException;
import com.bip.OneStopShop.models.OrderItem;
import com.bip.OneStopShop.models.Product;
import com.bip.OneStopShop.models.User;
import com.bip.OneStopShop.models.dtos.*;
import com.bip.OneStopShop.repositories.OrderRepository;
import com.bip.OneStopShop.repositories.ProductRepository;
import com.bip.OneStopShop.repositories.UserRepository;
import com.bip.OneStopShop.services.OrderService;
import com.bip.OneStopShop.services.mappers.OrderMapper;
import com.bip.OneStopShop.services.mappers.ProductMapper;
import com.bip.OneStopShop.services.mappers.UserMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            UserRepository userRepository,
            ProductRepository productRepository,
            OrderMapper orderMapper,
            UserMapper userMapper,
            ProductMapper productMapper
    ) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
        this.userMapper = userMapper;
        this.productMapper = productMapper;
    }

    public OrderListDto findAllOrders(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User does not exist."));
        UserResponseDto userResponseDto = userMapper.convertUserToUserResponseDto(user);

        List<OrderItem> orderItems = orderRepository.findAllByUserIdOrderByCreatedOnDesc(userId);
        double totalCost = 0.0;
        List<OrderItemResponseDto> orderItemResponseDtoList = new ArrayList<>();
        for(OrderItem orderItem : orderItems) {
            Product product = productRepository.findById(orderItem.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product does not exist."));
            ProductDto productDto = productMapper.convertProductToProductDto(product);

            totalCost += product.getPrice() * orderItem.getQuantity();

            OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto(productDto, orderItem.getQuantity());
            orderItemResponseDtoList.add(orderItemResponseDto);
        };

        return new OrderListDto(userResponseDto, orderItemResponseDtoList, totalCost);
    }

    public OrderItemResponseDto findOrderById(Integer id) {
        OrderItem orderItem = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found."));

        User user = userRepository.findById(orderItem.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User does not exist."));
        UserResponseDto userResponseDto = userMapper.convertUserToUserResponseDto(user);

        Product product = productRepository.findById(orderItem.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product does not exist."));
        ProductDto productDto = productMapper.convertProductToProductDto(product);

        return new OrderItemResponseDto(userResponseDto, productDto, orderItem.getQuantity());
    }

    public OrderItemDto placeOrder(OrderItemDto orderDto) {
        OrderItem order = orderMapper.convertOrderDtoToOrder(orderDto);
        OrderItem placedOrder = orderRepository.save(order);

        return orderMapper.convertOrderToOrderDto(placedOrder);
    }

    public void cancelOrder(Integer orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new OrderNotFoundException("Order does not exist.");
        }
    }

}
