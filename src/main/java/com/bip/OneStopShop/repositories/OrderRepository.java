package com.bip.OneStopShop.repositories;

import com.bip.OneStopShop.models.OrderItem;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderItem, Integer> {

    List<OrderItem> findAllByUserIdOrderByCreatedOnDesc(Integer userId);

}
