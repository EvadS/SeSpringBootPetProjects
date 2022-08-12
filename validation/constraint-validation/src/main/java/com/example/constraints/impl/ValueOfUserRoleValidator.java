package com.example.constraints.impl;

import com.example.constraints.ValueOfUserRole;
import com.example.enums.UserRole;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValueOfUserRoleValidator implements ConstraintValidator<ValueOfUserRole, Integer> {
   public void initialize(ValueOfUserRole constraint) {
   }

   @Override
   public boolean isValid(Integer obj, ConstraintValidatorContext context) {
         UserRole userRole = UserRole.of(obj);

         // TODO: good practice to use default value
      if(userRole == null)/*UserRole.NOT_SET)*/
         return  false;

      return true ;
   }
}
