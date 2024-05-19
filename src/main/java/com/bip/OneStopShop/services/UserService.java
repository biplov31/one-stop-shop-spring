package com.bip.OneStopShop.services;

import com.bip.OneStopShop.models.dtos.UserDto;
import com.bip.OneStopShop.models.dtos.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> findUsers();

    UserResponseDto findUserById(Integer id);

    UserResponseDto saveUser(UserDto userDto);

    UserResponseDto updateUser(Integer id, UserDto userDto);

    void deleteUser(Integer id);

}
