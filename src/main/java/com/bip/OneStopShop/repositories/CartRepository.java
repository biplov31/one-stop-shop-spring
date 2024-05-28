package com.bip.OneStopShop.repositories;

import com.bip.OneStopShop.models.CartItem;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface CartRepository extends ListCrudRepository<CartItem, Integer> {

    List<CartItem> findByUserIdOrderByCartItemIdDesc(Integer id);

    CartItem findByUserIdAndProductId(Integer userId, Integer productId);

    Boolean existsByCartItemId(Integer cartItemId);

    Boolean existsByUserIdAndProductId(Integer userId, Integer productId);

    @Modifying
    @Query("delete from \"cart\" where cart_item_id = :cartItemId") // when testing with H2, we need to make it explicit that the table name is all lowercase hence the double quotes
    void deleteByCartItemId(Integer cartItemId);

    // @Modifying
    // @Query("delete from cart where user_id = :userId and product_id = :productId")
    // Integer deleteByUserIdAndProductId(Integer userId, Integer productId); // derived delete queries are not yet supported in Spring Data JDBC

}
