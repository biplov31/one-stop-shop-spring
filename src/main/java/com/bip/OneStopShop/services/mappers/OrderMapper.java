package com.bip.OneStopShop.services.mappers;

import com.bip.OneStopShop.models.Order;
import com.bip.OneStopShop.models.OrderItem;
import com.bip.OneStopShop.models.dtos.OrderDto;
import com.bip.OneStopShop.models.dtos.OrderItemDto;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    public Order convertOrderDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setUserId(orderDto.getUserId());
        Set<OrderItem> orderItems = orderDto.getOrderItemDtos().stream()
                        .map(this::convertOrderItemDtoToOrderItem)
                        .collect(Collectors.toSet());
        order.setOrderItems(orderItems);

        return order;
    }

    public OrderItem convertOrderItemDtoToOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(orderItemDto.getProductId());
        orderItem.setQuantity(orderItemDto.getQuantity());

        return orderItem;
    }

    public OrderItemDto convertOrderItemToOrderItemDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setProductId(orderItem.getProductId());
        orderItemDto.setQuantity(orderItem.getQuantity());

        return orderItemDto;
    }

}
