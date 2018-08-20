package com.yuqiyu.lee.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * ======================== Created with IntelliJ IDEA. User：恒宇少年 Date：2017/4/16 Time：16:47 码云：http://git.oschina.net/jnyqy ========================
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = FlagValidatorClass.class)
public @interface FlagValidator {

  //flag的有效值多个使用','隔开
  String values();

  //提示内容
  String message() default "flag不存在";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
