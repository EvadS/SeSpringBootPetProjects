package com.se.sample.springboottetinggradle.beans;

import java.time.LocalDateTime;

import com.se.sample.springboottetinggradle.persistence.UserEntity;
import com.se.sample.springboottetinggradle.persistence.UserRepository;
import com.se.sample.springboottetinggradle.sesrvice.RegisterUseCase;
import com.se.sample.springboottetinggradle.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@SpringBootTest
class MockBeanTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private RegisterUseCase registerUseCase;

    @Test
    void testRegister() {
        // given
        User user = new User("Zaphod", "zaphod@galaxy.net");
        boolean sendWelcomeMail = true;
        given(userRepository.save(any(UserEntity.class))).willReturn(userEntity(1L));

        // when
        Long userId = registerUseCase.registerUser(user, sendWelcomeMail);

        // then
        assertThat(userId).isEqualTo(1L);
    }

    private UserEntity userEntity(Long id) {
        return new UserEntity(id, "Zaphod", "zaphod@galaxy.net", LocalDateTime.now());
    }
}