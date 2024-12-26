package com.example.beststore.services;

import com.example.beststore.dto.UserDto;
import com.example.beststore.entity.User;
import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
