package com.se.sample.springbootexceptionhandling.validation;


import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class IDExistingValidator implements ConstraintValidator<IDExisting, Long> {


    @Override
    public boolean isValid(Long productId, ConstraintValidatorContext context) {
        // TODO: for test
        //     return Objects.isNull(productId) || productService.findById(productId).isPresent();
        return false;
    }
}