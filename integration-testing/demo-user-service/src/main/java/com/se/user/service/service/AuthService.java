package com.se.user.service.service;

import java.security.Principal;

public interface AuthService {
    Principal getAuthenticatedUser(Principal principal);
}
