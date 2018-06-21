package com.lmonkiewicz.example.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Created by lmonkiewicz on 18.03.2017.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {InRangeValidator.class})
public @interface InRange {

  String message() default "Value is out of range";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  int min() default Integer.MIN_VALUE;

  int max() default Integer.MAX_VALUE;
}
