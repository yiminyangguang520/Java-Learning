package com.lmonkiewicz.example.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author lmonkiewicz
 * @date 18.03.2017
 */
@Component
public class InRangeValidator implements ConstraintValidator<InRange, Integer> {

  private int min;
  private int max;

  @Override
  public void initialize(InRange constraintAnnotation) {
    this.min = constraintAnnotation.min();
    this.max = constraintAnnotation.max();
  }

  @Override
  public boolean isValid(Integer value, ConstraintValidatorContext context) {
    return value == null || (value >= min && value <= max);
  }
}
