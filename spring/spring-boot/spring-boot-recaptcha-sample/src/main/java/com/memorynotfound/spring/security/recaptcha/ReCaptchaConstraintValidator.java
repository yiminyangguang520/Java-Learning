package com.memorynotfound.spring.security.recaptcha;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author min
 */
public class ReCaptchaConstraintValidator implements ConstraintValidator<ValidReCaptcha, String> {

  @Autowired
  private ReCaptchaService reCaptchaService;

  @Override
  public void initialize(ValidReCaptcha constraintAnnotation) {

  }

  @Override
  public boolean isValid(String reCaptchaResponse, ConstraintValidatorContext context) {

    if (reCaptchaResponse == null || reCaptchaResponse.isEmpty()) {
      return true;
    }

    return reCaptchaService.validate(reCaptchaResponse);
  }

}
