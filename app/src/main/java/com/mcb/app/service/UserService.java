package com.mcb.app.service;



import com.mcb.commons.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto user);
    UserDto getUserById(Long id);
    void deleteUser(Long id);
    List<UserDto> getAllUsers(int page, int size);
}