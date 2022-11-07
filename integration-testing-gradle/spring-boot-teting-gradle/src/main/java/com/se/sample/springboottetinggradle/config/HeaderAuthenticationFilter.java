package com.se.sample.springboottetinggradle.config;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

@AllArgsConstructor
public class HeaderAuthenticationFilter extends OncePerRequestFilter {

    private final String companyId;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerCompanyId = request.getHeader("int-companyId");
        if(Objects.equals(headerCompanyId, companyId)){
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(
                    new UsernamePasswordAuthenticationToken("user", "NA", List.of(new SimpleGrantedAuthority("USER"))));
            SecurityContextHolder.setContext(context);
            logger.info("Authenticate user for internal requests success");
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !(nonNull(request.getHeader("int-companyId")) &&
                request.getRequestURI().contains("/engage/tendlc-services/v2/internal/"));
    }
}
