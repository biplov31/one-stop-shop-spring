package com.bip.OneStopShop.controllers;

import com.bip.OneStopShop.models.dtos.OrderItemDto;
import com.bip.OneStopShop.models.dtos.OrderItemResponseDto;
import com.bip.OneStopShop.models.dtos.OrderListDto;
import com.bip.OneStopShop.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<OrderListDto> getAllOrders(@PathVariable Integer userId) {
        return new ResponseEntity<>(orderService.findAllOrders(userId), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderItemResponseDto> getOrderById(@PathVariable Integer orderId) {
        return new ResponseEntity<>(orderService.findOrderById(orderId), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderItemDto placeOrder(@Valid @RequestBody OrderItemDto orderDto) {
        return orderService.placeOrder(orderDto);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelOrder(@PathVariable Integer orderId) {
        orderService.cancelOrder(orderId);
    }

}
