package com.se.sample;

import com.se.sample.models.request.ProductRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Component
@AllArgsConstructor
public class ProductValidator {

    private final Validator validator;

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
}