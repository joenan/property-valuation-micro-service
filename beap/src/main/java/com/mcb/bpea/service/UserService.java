package com.mcb.bpea.service;

import com.mcb.bpea.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto user);
    UserDto getUserById(Long id);
    void deleteUser(Long id);
    List<UserDto> getAllUsers(int page, int size);
}