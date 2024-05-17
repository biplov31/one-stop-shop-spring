package com.bip.OneStopShop.controllers;

import com.bip.OneStopShop.models.dtos.UserDto;
import com.bip.OneStopShop.models.dtos.UserResponseDto;
import com.bip.OneStopShop.services.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl service) {
        this.userService = service;
    }

    @GetMapping
    public List<UserResponseDto> getUsers() {
        return userService.findUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto postUser(@Valid @RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable Integer id, @Valid @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

}
