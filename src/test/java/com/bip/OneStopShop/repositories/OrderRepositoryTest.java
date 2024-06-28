package com.bip.OneStopShop.repositories;

import com.bip.OneStopShop.models.OrderItem;
import com.bip.OneStopShop.models.Product;
import com.bip.OneStopShop.models.ProductCategory;
import com.bip.OneStopShop.models.User;
import com.bip.OneStopShop.models.dtos.OrderItemResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

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

    @BeforeEach
    void init() {
        userOne = new User("John", "Doe", "john123", "john@gmail.com");
        userRepository.save(userOne);

        productOne = new Product("Product 1", "This is Product 1.", 25.0, ProductCategory.MENS_CLOTHING.name());
        productRepository.save(productOne);

        productTwo = new Product("Product 2", "This is Product 2.", 25.0, ProductCategory.MENS_CLOTHING.name());
        productRepository.save(productTwo);

        orderItemOne = new OrderItem();
        // orderItemOne.setUserId(userOne.getId());
        orderItemOne.setProductId(productOne.getId());
        orderItemOne.setQuantity(5);

        orderItemTwo = new OrderItem();
        // orderItemTwo.setUserId(userOne.getId());
        orderItemTwo.setProductId(productTwo.getId());
        orderItemTwo.setQuantity(10);

        orderRepository.save(orderItemOne);
        orderRepository.save(orderItemTwo);
    }

    @Test
    @DisplayName("This test should find a specific order by its ID.")
    void shouldFindByOrderId() {
        List<OrderItemResponseDto> orderItemResponseDtoList = orderRepository.findByOrderId(orderItemOne.getId());

        assertThat(orderItemResponseDtoList).isNotNull();
        assertThat(orderItemResponseDtoList.get(0).getTitle()).isEqualTo(productOne.getTitle());
    }

    @Test
    @DisplayName("This test should find all the orders made by a user, newer first.")
    void shouldFindAllOrdersByUserIdOrderedByCreatedAtDescending() {
        List<OrderItemResponseDto> orderItemResponseDtoList = orderRepository.findAllByUserIdOrderByCreatedAtDesc(userOne.getId());

        assertThat(orderItemResponseDtoList.size()).isEqualTo(2);
        assertThat(orderItemResponseDtoList.get(0).getTitle()).isEqualTo(productOne.getTitle());
        assertThat(orderItemResponseDtoList.get(1).getTitle()).isEqualTo(productTwo.getTitle());
    }

}
