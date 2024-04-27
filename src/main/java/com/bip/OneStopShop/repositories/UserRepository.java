package com.bip.OneStopShop.repositories;

import com.bip.OneStopShop.models.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends ListCrudRepository<User, Integer> {

}
