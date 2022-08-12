package com.example.constraints;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.example.enums.CustomerType;
import com.example.model.Customer;
import org.junit.Test;

import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Set;


public class CustomerTypeSubSetValidatorUnitTest {

    private static Validator validator;

    @BeforeClass
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
    }

    @Test
    public void whenEnumAnyOfSubset_thenShouldNotReportConstraintViolations() {
        Customer customer =  Customer.builder()
                .customerTypeOfSubset(CustomerType.NEW)
                .build();
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertThat(violations.isEmpty()).isTrue();
    }



}