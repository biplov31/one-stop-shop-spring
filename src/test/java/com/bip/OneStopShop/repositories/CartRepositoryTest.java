package com.bip.OneStopShop.repositories;

import com.bip.OneStopShop.models.CartItem;
import com.bip.OneStopShop.models.Product;
import com.bip.OneStopShop.models.ProductCategory;
import com.bip.OneStopShop.models.User;
import org.h2.tools.Server;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CartRepositoryTest {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    private User userOne;
    private User userTwo;
    private Product productOne;
    private Product productTwo;
    private CartItem cartItemOne;
    private CartItem cartItemTwo;

    @BeforeAll
    public static void initTest() throws SQLException {
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082")
                .start();
    }

    @BeforeEach
    void init() {
        userOne = new User("John", "Doe", "john123", "john@gmail.com");
        userRepository.save(userOne);

        userTwo = new User("Jane", "Doe", "jane123", "jane@gmail.com");
        userRepository.save(userTwo);

        productOne = new Product("Product 1", "This is Product 1.", 25.0, ProductCategory.MENS_CLOTHING.name());
        productRepository.save(productOne);

        productTwo = new Product("Product 2", "This is Product 2.", 25.0, ProductCategory.MENS_CLOTHING.name());
        productRepository.save(productTwo);

        cartItemOne = new CartItem();
        cartItemOne.setUserId(userOne.getId());
        cartItemOne.setProductId(productOne.getId());
        cartItemOne.setQuantity(4);

        cartItemTwo = new CartItem();
        cartItemTwo.setUserId(userOne.getId());
        cartItemTwo.setProductId(productTwo.getId());
        cartItemTwo.setQuantity(2);

        cartRepository.save(cartItemOne);
        cartRepository.save(cartItemTwo);
    }

    @Test
    void shouldFindByUserIdOrderByCartItemIdDesc() {
        List<CartItem> cartItemList = cartRepository.findByUserIdOrderByCartItemIdDesc(userOne.getId());

        assertThat(cartItemList).isNotNull();
        assertThat(cartItemList.get(0).getCartItemId()).isEqualTo(cartItemTwo.getCartItemId());
    }

    @Test
    void shouldFindByUserIdAndProductId_AndReturnCartItem() {
        CartItem retrievedCartItem = cartRepository.findByUserIdAndProductId(userOne.getId(), productOne.getId());

        assertThat(retrievedCartItem).isNotNull();
        assertThat(retrievedCartItem.getCartItemId()).isEqualTo(cartItemOne.getCartItemId());
    }

    @Test
    void shouldCheckExistenceByCartItemId_AndReturnBoolean() {
        Boolean doesExist = cartRepository.existsByCartItemId(cartItemOne.getCartItemId());

        assertThat(doesExist).isTrue();
    }

    @Test
    void shouldCheckExistenceByUserIdAndProductId_AndReturnBoolean() {
        Boolean doesExist = cartRepository.existsByUserIdAndProductId(userTwo.getId(), productOne.getId());

        assertThat(doesExist).isFalse();
    }

    @Test
    void shouldDeleteByCartItemId() {
        cartRepository.deleteByCartItemId(cartItemOne.getCartItemId());

        Boolean doesExist = cartRepository.existsByCartItemId(cartItemOne.getCartItemId());

        assertThat(doesExist).isFalse();
    }
}
