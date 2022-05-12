package com.example.filedemo.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ImageFileValidator.class})
public @interface ValidImage {
    String message() default "Invalid image file 0000000000000";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
