package com.example.webmvctestdemo.persistence;

import com.example.webmvctestdemo.domain.SaveUserPort;
import com.example.webmvctestdemo.domain.User;
import org.springframework.stereotype.Component;

@Component
public class PersistenceAdapter implements SaveUserPort {
    @Override
    public Long saveUser(User user) {
        return null;
    }
}
