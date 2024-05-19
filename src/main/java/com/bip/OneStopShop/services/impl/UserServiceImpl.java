package com.bip.OneStopShop.services.impl;

import com.bip.OneStopShop.exceptions.UserNotFoundException;
import com.bip.OneStopShop.models.User;
import com.bip.OneStopShop.models.dtos.UserDto;
import com.bip.OneStopShop.models.dtos.UserResponseDto;
import com.bip.OneStopShop.repositories.UserRepository;
import com.bip.OneStopShop.services.UserService;
import com.bip.OneStopShop.services.mappers.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository repository, UserMapper userMapper) {
        this.userRepository = repository;
        this.userMapper = userMapper;
    }

    public List<UserResponseDto> findUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> userMapper.convertUserToUserResponseDto(user))
                .collect(Collectors.toList());
    }

    public UserResponseDto findUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User does not exist."));
        return userMapper.convertUserToUserResponseDto(user);
    }

    public UserResponseDto saveUser(UserDto userDto) {
        User user = userMapper.convertUserDtoToUser(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.convertUserToUserResponseDto(savedUser);
    }

    public UserResponseDto updateUser(Integer id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User does not exist."));
        User userToUpdate = userMapper.convertUserDtoToUser(userDto);
        userToUpdate.setId(id);
        User updatedUser = userRepository.save(userToUpdate);
        return userMapper.convertUserToUserResponseDto(updatedUser);
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User does not exist.");
        }
        userRepository.deleteById(id);
    }
}
