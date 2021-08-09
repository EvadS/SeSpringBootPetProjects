package com.se.product.service.controller;

import com.se.product.service.exception.UserLoginException;
import com.se.product.service.model.CustomUserDetails;
import com.se.product.service.model.payload.JwtAuthenticationResponse;
import com.se.product.service.model.payload.LoginRequest;
import com.se.product.service.security.JwtTokenProvider;
import com.se.product.service.service.impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authorization Rest API",
        description = "Defines endpoints that can be hit only when the user is not logged in. It's not secured by default.")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthServiceImpl authService;
    private final JwtTokenProvider tokenProvider;


    public AuthController(AuthServiceImpl authService, JwtTokenProvider tokenProvider) {
        this.authService = authService;
        this.tokenProvider = tokenProvider;
    }

    /**
     * Entry point for the user log in. Return the jwt auth token and the refresh token
     */
    @PostMapping("/login")
    @Operation(summary = "authenticate",
            description = "Logs the user in to the system and return the auth tokens")
    public ResponseEntity authenticateUser(
            @Parameter(name = "loginRequest", description = "The LoginRequest payload")
            @Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authService.authenticateUser(loginRequest)
                .orElseThrow(() -> new UserLoginException("Couldn't login user [" + loginRequest + "]"));

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        logger.info("Logged in User returned [API]: " + customUserDetails.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);


        String jwtToken = authService.generateToken(customUserDetails);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwtToken, tokenProvider.getExpiryDuration()));
    }


}
