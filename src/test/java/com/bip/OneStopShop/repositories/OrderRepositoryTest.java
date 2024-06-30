package com.bip.OneStopShop.repositories;

import com.bip.OneStopShop.models.*;
import com.bip.OneStopShop.models.dtos.OrderItemResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    private User userOne;
    private Product productOne;
    private Product productTwo;
    private OrderItem orderItemOne;
    private OrderItem orderItemTwo;
    private Set<OrderItem> orderItems = new HashSet<>();
    private Order order;
    private Double totalCost;

    @BeforeEach
    void init() {
        userOne = new User("John", "Doe", "john123", "john@gmail.com");
        userRepository.save(userOne);

        productOne = new Product("Product 1", "This is Product 1.", 25.0, ProductCategory.MENS_CLOTHING.name());
        productRepository.save(productOne);

        productTwo = new Product("Product 2", "This is Product 2.", 30.0, ProductCategory.MENS_CLOTHING.name());
        productRepository.save(productTwo);

        orderItemOne = new OrderItem(productOne.getId(), productOne.getPrice(), 10);
        orderItems.add(orderItemOne);
        orderItemTwo = new OrderItem(productTwo.getId(), productTwo.getPrice(), 5);
        orderItems.add(orderItemTwo);

        totalCost = orderItemOne.getQuantity() * orderItemOne.getPrice() + orderItemTwo.getQuantity() * orderItemTwo.getPrice();

        order = new Order(userOne.getId(), orderItems, totalCost);

        orderRepository.save(order);
    }

    @Test
    @DisplayName("This test should find a specific order by its ID.")
    void shouldFindByOrderId() {
        List<OrderItemResponseDto> orderItemResponseDtoList = orderRepository.findByOrderId(order.getId());

        assertThat(orderItemResponseDtoList).isNotNull();
        assertThat(orderItemResponseDtoList.get(0).getTitle()).isEqualTo(productOne.getTitle());
    }

    @Test
    @DisplayName("This test should find all the orders made by a user, newer first.")
    void shouldFindAllOrdersByUserIdOrderedByCreatedAtDescending() {
        List<OrderItemResponseDto> orderItemResponseDtoList = orderRepository.findAllByUserIdOrderByCreatedAtDesc(userOne.getId());

        assertThat(orderItemResponseDtoList.size()).isEqualTo(2);
        assertThat(orderItemResponseDtoList.get(0).getTitle()).isEqualTo(productTwo.getTitle());
        assertThat(orderItemResponseDtoList.get(1).getTitle()).isEqualTo(productOne.getTitle());
    }

}
