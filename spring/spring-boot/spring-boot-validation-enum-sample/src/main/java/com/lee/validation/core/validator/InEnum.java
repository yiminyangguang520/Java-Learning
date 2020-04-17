package com.lee.validation.core.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author min
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = InEnumValidator.class)
public @interface InEnum {

  /**
   * @return 实现 IntArrayValuable 接口的
   */
  Class<? extends IntArrayValuable> value();

  /**
   * @return 提示内容
   */
  String message() default "必须在指定范围 {value}";

  /**
   * @return 分组
   */
  Class<?>[] groups() default {};

  /**
   * @return Payload 数组
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code @InEnum} constraints on the same element.
   */
  @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  @interface List {

    InEnum[] value();

  }

}
