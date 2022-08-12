package com.se.demo.auth.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        int a =0;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println("---- principal " + principal.toString());
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
