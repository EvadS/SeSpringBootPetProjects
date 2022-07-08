package com.se.user.service.service.impl;

import com.se.user.service.model.User;
import com.se.user.service.repository.UserRepository;
import com.se.user.service.service.UserService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserServiceImpl  implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        return Optional.ofNullable(principal)
                .map(p -> userRepository.findUserByUsername(p.getName())).orElse(null);
    }
}
