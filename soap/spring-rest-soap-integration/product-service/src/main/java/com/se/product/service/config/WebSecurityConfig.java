package com.se.product.service.config;

import com.se.product.service.security.JwtAuthenticationEntryPoint;
import com.se.product.service.security.JwtAuthenticationFilter;
import com.se.product.service.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Profile("!dev")
@Configuration
//@EnableWebSecurity(debug = false)
@EnableJpaRepositories(basePackages = "com.se.product.service.repository")
@EnableGlobalMethodSecurity(
        securedEnabled = true, // TODO se :
        jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;

    private final JwtAuthenticationEntryPoint jwtEntryPoint;



    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };

    @Autowired
    public WebSecurityConfig(CustomUserDetailsService userDetailsService, JwtAuthenticationEntryPoint jwtEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.jwtEntryPoint = jwtEntryPoint;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/v3/api-docs/*",
                "/swagger-ui","/swagger-ui/",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/webjars/**", "/webjars/swagger-ui/**");
    }


    // TODO: se hardcoded to teseting
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/h2/**").permitAll()
//                .antMatchers("/",
//                		"/favicon.ico",
//                        "/**/*.json",
//                        "/**/*.xml",
//                        "/**/*.properties",
//                        "/**/*.woff2",
//                        "/**/*.woff",
//                        "/**/*.ttf",
//                        "/**/*.ttc",
//                        "/**/*.ico",
//                        "/**/*.bmp",
//                        "/**/*.png",
//                        "/**/*.gif",
//                        "/**/*.svg",
//                        "/**/*.jpg",
//                        "/**/*.jpeg",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js").permitAll()
//                .antMatchers("/**/api/auth/**").permitAll()
//                .antMatchers("/**/ws/**").permitAll()
//                .antMatchers("/price/**").permitAll()
//                .antMatchers("/api/**").permitAll()
//                .antMatchers("/api/product**").permitAll()
         //       .antMatchers("/auth/admin/*").hasRole("ADMIN")
         //       .antMatchers("/auth/*").hasAnyRole("ADMIN","USER")
//

                //TODO: temporary solution to test ws
                .anyRequest().authenticated();

        http.headers().frameOptions().sameOrigin();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
