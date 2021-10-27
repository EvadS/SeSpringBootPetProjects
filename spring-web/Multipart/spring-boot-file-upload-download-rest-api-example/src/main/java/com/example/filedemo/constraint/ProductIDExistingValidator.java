package com.example.filedemo.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductIDExistingValidator implements ConstraintValidator<ProductIDExisting, String> {
   public void initialize(ProductIDExisting constraint) {
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {
      return false;
   }
}
