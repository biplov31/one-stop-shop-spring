package com.bip.OneStopShop.repositories;

import com.bip.OneStopShop.models.Order;
import com.bip.OneStopShop.models.OrderItem;
import com.bip.OneStopShop.models.dtos.OrderItemResponseDto;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface OrderRepository extends ListCrudRepository<Order, Integer> {

    // without wrapping the table names with double-inverted commas, we get the error while testing: org.h2.jdbc.JdbcSQLSyntaxErrorException: Table "PRODUCT" not found (candidates are: "product")
    @Query("""
            select * from "user_order"
            join "product" on "user_order".product_id = "product".id
            where "user_order".id = :orderId;
            """)
    List<OrderItemResponseDto> findByOrderId(Integer orderId);

    // group by order ID so we can separate products belonging to different orders
    @Query("""
            select * from "user_order"
            join "product" on "user_order".product_id = "product".id
            where "user_order".user_id = :userId
            order by "user_order".created_at desc;
            """)
    List<OrderItemResponseDto> findAllByUserIdOrderByCreatedAtDesc(Integer userId);
    // should return a list of all the orders made by a user in different points in time; each order may have one or more products

}
