package com.bip.OneStopShop.services.impl;

import com.bip.OneStopShop.exceptions.OrderNotFoundException;
import com.bip.OneStopShop.exceptions.UserNotFoundException;
import com.bip.OneStopShop.models.Order;
import com.bip.OneStopShop.models.OrderItem;
import com.bip.OneStopShop.models.User;
import com.bip.OneStopShop.models.dtos.*;
import com.bip.OneStopShop.repositories.OrderRepository;
import com.bip.OneStopShop.repositories.UserRepository;
import com.bip.OneStopShop.services.OrderService;
import com.bip.OneStopShop.services.mappers.OrderMapper;
import com.bip.OneStopShop.services.mappers.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final UserMapper userMapper;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, OrderMapper orderMapper, UserMapper userMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
        this.userMapper = userMapper;
    }

    public OrderListDto findAllOrders(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User does not exist."));
        UserResponseDto userResponseDto = userMapper.convertUserToUserResponseDto(user);

        List<OrderItemResponseDto> orderItemResponseDtoList = orderRepository.findAllByUserIdOrderByCreatedAtDesc(userId);
        double totalCost = 0.0;
        // List<OrderItemResponseDto> orderItemResponseDtoList = new ArrayList<>();
        for(OrderItemResponseDto orderItem : orderItemResponseDtoList) {
            totalCost += orderItem.getPrice() * orderItem.getQuantity();
        }

        return new OrderListDto(userResponseDto, orderItemResponseDtoList, totalCost);
    }

    // N+1 query problem
    // public OrderListDto findAllOrders(Integer userId) {
    //     User user = userRepository.findById(userId)
    //             .orElseThrow(() -> new UserNotFoundException("User does not exist."));
    //     UserResponseDto userResponseDto = userMapper.convertUserToUserResponseDto(user);
    //
    //     List<OrderItem> orderItems = orderRepository.findAllByUserIdOrderByCreatedAtDesc(userId);
    //     double totalCost = 0.0;
    //     List<OrderItemResponseDto> orderItemResponseDtoList = new ArrayList<>();
    //     for(OrderItem orderItem : orderItems) {
    //         Product product = productRepository.findById(orderItem.getProductId())
    //                 .orElseThrow(() -> new ProductNotFoundException("Product does not exist."));
    //         ProductDto productDto = productMapper.convertProductToProductDto(product);
    //
    //         totalCost += product.getPrice() * orderItem.getQuantity();
    //
    //         OrderItemResponseDto orderItemResponseDto = new OrderItemResponseDto(productDto, orderItem.getQuantity());
    //         orderItemResponseDtoList.add(orderItemResponseDto);
    //     };
    //
    //     return new OrderListDto(userResponseDto, orderItemResponseDtoList, totalCost);
    // }

    public OrderListDto findOrderById(Integer id) {
        List<OrderItemResponseDto> orderItemResponseDtoList = orderRepository.findByOrderId(id);
        double totalCost = 0.0;
        for(OrderItemResponseDto orderItem : orderItemResponseDtoList) {
            totalCost += orderItem.getPrice() * orderItem.getQuantity();
        }

        return new OrderListDto(orderItemResponseDtoList, totalCost);

        // N+1 query problem
        // OrderItem orderItem = orderRepository.findById(id)
        //         .orElseThrow(() -> new OrderNotFoundException("Order not found."));
        //
        // User user = userRepository.findById(orderItem.getUserId())
        //         .orElseThrow(() -> new UserNotFoundException("User does not exist."));
        // UserResponseDto userResponseDto = userMapper.convertUserToUserResponseDto(user);
        //
        // Product product = productRepository.findById(orderItem.getProductId())
        //         .orElseThrow(() -> new ProductNotFoundException("Product does not exist."));
        // ProductDto productDto = productMapper.convertProductToProductDto(product);
        // return new OrderItemResponseDto(userResponseDto, productDto, orderItem.getQuantity());
    }

    public OrderListDto placeOrder(OrderDto orderDto) {
        Order order = orderMapper.convertOrderDtoToOrder(orderDto);
        Order placedOrder = orderRepository.save(order);

        return findOrderById(placedOrder.getId());
    }

    // users should also be able to place an order for multiple products at once from their cart

    public void cancelOrder(Integer orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new OrderNotFoundException("Order does not exist.");
        }
    }

}
