package com.bip.OneStopShop.services;

import com.bip.OneStopShop.models.dtos.OrderItemDto;
import com.bip.OneStopShop.models.dtos.OrderItemResponseDto;
import com.bip.OneStopShop.models.dtos.OrderListDto;

import java.util.List;

public interface OrderService {

    OrderListDto findAllOrders(Integer userId);

    OrderItemResponseDto findOrderById(Integer id);

    OrderItemDto placeOrder(OrderItemDto orderDto);

    // OrderDto updateOrder(OrderDto orderDto);

    void cancelOrder(Integer id);

}
