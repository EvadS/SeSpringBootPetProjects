package com.se.product.service.config;

import com.se.product.service.controller.base.CategoryApi;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(ApplicationConstant.PATH_PREFIX, HandlerTypePredicate.forBasePackageClass(CategoryApi.class));
    }
}
