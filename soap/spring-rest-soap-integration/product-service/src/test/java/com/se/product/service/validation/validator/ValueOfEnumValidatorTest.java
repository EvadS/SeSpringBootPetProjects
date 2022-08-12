package com.se.product.service.validation.validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


import static org.assertj.core.api.Assertions.assertThat;

        import java.util.Set;

        import javax.validation.ConstraintViolation;
        import javax.validation.Validation;
        import javax.validation.Validator;


import com.se.product.service.domain.Price;
import com.se.product.service.model.enums.CurrencyType;
import org.junit.BeforeClass;
        import org.junit.Test;


public class ValueOfEnumValidatorTest {

    private static Validator validator;

    @BeforeClass
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
    }

    @Test
    public void whenStringAnyOfEnum_thenShouldNotReportConstraintViolations() {
//       Price price =  Price.builder()
//                .currencyType(CurrencyType.EUR)
//                .customerTypeOfSubset(CustomerType.NEW)
//                .build();
//        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
//        assertThat(violations.isEmpty()).isTrue();
    }

    @Test
    public void whenStringNull_thenShouldNotReportConstraintViolations() {
//        Customer customer = Customer.builder()
//                .customerTypeOfSubset(CustomerType.NEW)
//                .customerTypeString(null)
//                .build();
//        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
//        assertThat(violations.isEmpty()).isTrue();
    }

    @Test
    public void whenStringNotAnyOfEnum_thenShouldGiveOccurrenceOfConstraintViolations() {
//        Customer customer =  Customer.builder()
//                .customerTypeOfSubset(CustomerType.NEW)
//                .customerTypeString("test")
//                .build();
//        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
//        assertThat(violations.size()).isEqualTo(1);

//        assertThat(violations).anyMatch(CustomerUnitTest.havingPropertyPath("customerTypeString")
//                .and(CustomerUnitTest.havingMessage("must be any of enum class com.baeldung.javaxval.enums.demo.CustomerType")));
    }
}