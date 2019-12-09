package com.memorynotfound.spring.security.recaptcha;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author litz-a
 */
@Documented
@Constraint(validatedBy = ReCaptchaConstraintValidator.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface ValidReCaptcha {

  String message() default "Invalid ReCaptcha";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}