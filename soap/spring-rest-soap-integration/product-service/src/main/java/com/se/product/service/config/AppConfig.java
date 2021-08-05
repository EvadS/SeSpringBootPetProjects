package com.se.product.service.config;

<<<<<<< HEAD
import com.se.product.service.constatn.GeneralConstants;
import com.se.product.service.controller.base.CategoryApi;
import com.se.product.service.controller.base.PriceApi;
import com.se.product.service.controller.base.ProductApi;
=======
import com.se.product.service.controller.base.CategoryApi;
>>>>>>> master
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

<<<<<<< HEAD
@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(GeneralConstants.PATH_PREFIX, HandlerTypePredicate.forBasePackageClass(CategoryApi.class));
        configurer.addPathPrefix(GeneralConstants.PATH_PREFIX, HandlerTypePredicate.forBasePackageClass(PriceApi.class));
        configurer.addPathPrefix(GeneralConstants.PATH_PREFIX, HandlerTypePredicate.forBasePackageClass(ProductApi.class));
    }
}
=======

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(ApplicationConstant.PATH_PREFIX, HandlerTypePredicate.forBasePackageClass(CategoryApi.class));
    }
}
>>>>>>> master
