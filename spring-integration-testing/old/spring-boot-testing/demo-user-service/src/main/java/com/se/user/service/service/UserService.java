package com.se.user.service.service;

import com.se.user.service.model.User;

import java.security.Principal;

public interface UserService {
    User getUserByPrincipal(Principal principal);
}
