package com.se.sample.springbootexceptionhandling.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy = IDExistingValidator.class)
public @interface IDExisting {
    String message() default "{IDExisting}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
