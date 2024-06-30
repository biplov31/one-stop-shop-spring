package com.bip.OneStopShop.repositories;

import com.bip.OneStopShop.models.Order;
import com.bip.OneStopShop.models.OrderItem;
import com.bip.OneStopShop.models.dtos.OrderItemResponseDto;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface OrderRepository extends ListCrudRepository<Order, Integer> {

    // without wrapping the table names with double-inverted commas, we get the error while testing: org.h2.jdbc.JdbcSQLSyntaxErrorException: Table "PRODUCT" not found (candidates are: "product")
    // order_id needs to be double-quoted because it is declared as a @Transient field, so we run into uppercase/lowercase issue when testing with H2
    @Query("""
            select * from "user_order"
            join "order_item" on "user_order".id = "order_item"."order_id"
            join "product" on "order_item".product_id = "product".id
            where "order_item"."order_id" = :orderId;
            """)
    List<OrderItemResponseDto> findByOrderId(Integer orderId);

    // group by order ID so we can separate products belonging to different orders
    @Query("""
            select * from "user_order"
            join "user_account" on "user_order".user_id = "user_account".id
            join "order_item" on "user_order".id = "order_item"."order_id"
            join "product" on "order_item".product_id = "product".id
            where "user_order".user_id = :userId
            order by "user_order".created_at desc;
            """)
    List<OrderItemResponseDto> findAllByUserIdOrderByCreatedAtDesc(Integer userId);
    // should return a list of all the orders made by a user in different points in time; each order may have one or more products

}
