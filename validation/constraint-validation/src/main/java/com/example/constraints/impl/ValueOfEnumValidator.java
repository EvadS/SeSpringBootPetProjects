package com.example.constraints.impl;

import com.example.constraints.ValueOfEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {
   private List<String> acceptedValues;

   @Override
   public void initialize(ValueOfEnum annotation) {
      acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
              .map(Enum::name)
              .collect(Collectors.toList());

      List lst = new ArrayList();
      for(String val  : acceptedValues){


      }
   }

   @Override
   public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
      if (value == null) {
         return true;
      }

      return acceptedValues.contains(value.toString());
   }
}