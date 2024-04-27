package com.bip.OneStopShop.services;

import com.bip.OneStopShop.models.User;
import com.bip.OneStopShop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
