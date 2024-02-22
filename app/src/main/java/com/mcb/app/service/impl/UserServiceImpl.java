package com.mcb.app.service.impl;

import com.mcb.app.converter.UserConverter;
import com.mcb.app.repository.UserRepository;
import com.mcb.app.service.UserService;
import com.mcb.commons.dto.UserDto;
import com.mcb.commons.entities.User;
import com.mcb.commons.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = userConverter.toUser(userDto);
        User savedUser = userRepository.save(user);
        return userConverter.toUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userConverter::toUserDto)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public List<UserDto> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> customerPage = userRepository.findAll(pageable);
        return customerPage.getContent()
                .stream()
                .map(userConverter::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }

}