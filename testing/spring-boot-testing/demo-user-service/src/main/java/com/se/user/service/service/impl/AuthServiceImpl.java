package com.se.user.service.service.impl;

import com.se.user.service.service.AuthService;
import org.springframework.stereotype.Service;

import java.security.Principal;


@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public Principal getAuthenticatedUser(Principal principal) {

        Principal muPrincipal = new Principal() {
            @Override
            public String getName() {
                return "user";
            }
        };

        return principal == null ? muPrincipal : principal;
    }
}
