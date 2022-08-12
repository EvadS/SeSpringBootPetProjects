package com.se.specification.example.service;

/**
 * @author Evgeniy Skiba on 20.06.2020
 * @project spring-data-jpa
 */

import com.se.specification.example.controller.dto.UserDto;
import com.se.specification.example.controller.dto.UserListRequest;
import com.se.specification.example.controller.mapper.UserMapper;
import com.se.specification.example.controller.specification.UserListSpecification;
import com.se.specification.example.domain.User;
import com.se.specification.example.repository.UserRepository;
import com.se.specification.example.service.errors.ErrorResponse;
import io.vavr.control.Either;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserListSpecification userListSpecification;

    public UserService(UserRepository userRepository, UserMapper userMapper, UserListSpecification userListSpecification) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userListSpecification = userListSpecification;
    }

    public Either<ErrorResponse, Page<UserDto>> findAll(UserListRequest request, Pageable pageable) {
        Page<User> userPage = userRepository.findAll(userListSpecification.getFilter(request), pageable);
        Page<UserDto> map = userPage.map(userMapper::map);
        return Either.right( map);
    }


}
