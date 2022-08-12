package com.se.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created by Evgeniy Skiba on 21.04.21
 */
// NEW CODE ABOVE REPLACES THIS! (2015-12-04)
@Configuration
public class MyAppConfig {
    @Bean  // Magic entry
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet ds = new DispatcherServlet();
        ds.setThrowExceptionIfNoHandlerFound(true);
        return ds;
    }
}