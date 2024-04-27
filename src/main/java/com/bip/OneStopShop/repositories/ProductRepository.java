package com.bip.OneStopShop.repositories;

import com.bip.OneStopShop.models.Product;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductRepository extends ListCrudRepository<Product, Integer> {

}
