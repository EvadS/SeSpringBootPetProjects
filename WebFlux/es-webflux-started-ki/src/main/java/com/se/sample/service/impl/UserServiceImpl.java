package com.se.sample.service.impl;

import com.se.sample.entity.dto.UserDto;
import com.se.sample.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class UserServiceImpl implements UserService {
//    @Autowired
//    private UserRepository userRepository;

    @Override
    public Mono<UserDto> getUser(String email) {
//        return userRepository.findUserByEmail(email)
//                .map(UserMapper.INSTANCE::toDTO)
//                .switchIfEmpty(Mono.error(UserNotFoundException::new));

        return Mono.empty();
    }
}
