package com.se.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import com.se.sample.models.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;


@Component
public class ProductValidator {

    @Autowired
    private Validator validator;

    public List<String> validateEmployee(ProductRequest employeeDTO) {

        final Set<ConstraintViolation<ProductRequest>> errors = validator.validate(employeeDTO);
        if (!errors.isEmpty()) {
            throw new ConstraintViolationException("EmployeeDTO is not valid", errors);
        }

        // Add business validations if any
        final List<String> customErrors = new ArrayList<>();
        // TODO:validate employee type
        return customErrors;

    }

    @PostMapping
    private void init(){

        System.out.println("*** HERE!!!!!");
    }
}