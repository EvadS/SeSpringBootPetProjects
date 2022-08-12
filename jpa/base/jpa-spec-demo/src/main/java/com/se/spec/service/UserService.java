package com.se.spec.service;

import com.se.spec.dto.UserDto;
import com.se.spec.dto.UserListRequest;
import com.se.spec.mapper.UserMapper;
import com.se.spec.repository.UserRepository;
import com.se.spec.specification.UserListSpecification;
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

    public Page<UserDto> findAll(UserListRequest request, Pageable pageable) {
        Page<UserDto> userPage = userRepository.findAll(userListSpecification.getFilter(request), pageable)
                .map(userMapper::map);
        return userPage;
    }

    /*
         return adminUserRepo.findAll(adminUserSpecification.getFilter(request), pageable)
                .map(adminUserRequestMapper::adminUserToAdminUserResponse);
     */
}
