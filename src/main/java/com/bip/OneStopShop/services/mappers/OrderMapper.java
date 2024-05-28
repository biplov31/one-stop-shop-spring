package com.bip.OneStopShop.services.mappers;

import com.bip.OneStopShop.models.OrderItem;
import com.bip.OneStopShop.models.dtos.OrderItemDto;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public OrderItem convertOrderDtoToOrder(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setUserId(orderItemDto.getUserId());
        orderItem.setProductId(orderItemDto.getProductId());
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setCreatedOn();

        return orderItem;
    }

    public OrderItemDto convertOrderToOrderDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setUserId(orderItem.getUserId());
        orderItemDto.setProductId(orderItem.getProductId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setCreatedOn(orderItem.getCreatedOn());

        return orderItemDto;
    }

}
