package com.example.testing.persistence;

import com.example.testing.domain.SaveUserPort;
import com.example.testing.domain.User;
import org.springframework.stereotype.Component;

@Component
public class PersistenceAdapter implements SaveUserPort {
    @Override
    public Long saveUser(User user) {
        return null;
    }
}
