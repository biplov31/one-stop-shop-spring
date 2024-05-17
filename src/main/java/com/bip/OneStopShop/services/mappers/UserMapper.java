package com.bip.OneStopShop.services.mappers;

import com.bip.OneStopShop.models.User;
import com.bip.OneStopShop.models.dtos.UserDto;
import com.bip.OneStopShop.models.dtos.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User convertUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return user;
    }

    public UserResponseDto convertUserToUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setFirstname(user.getFirstname());
        userResponseDto.setLastname(user.getLastname());
        userResponseDto.setEmail(user.getEmail());

        return userResponseDto;
    }
}
