package com.example.constraints;

import static org.junit.jupiter.api.Assertions.*;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;


import com.example.enums.CustomerType;
import com.example.model.Customer;
import com.example.model.UserRoleModel;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserRoleValidatorTest {
    private static Validator validator;

    @BeforeClass
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
    }

    @Test
    public void whenIntCodeNotFound_thenShouldNotReportConstraintViolations() {
        UserRoleModel userRoleModel =  UserRoleModel.builder()
                .userRoleCode(5)
                .build();
        Set<ConstraintViolation<UserRoleModel>> violations = validator.validate(userRoleModel);

        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(1);
    }

}