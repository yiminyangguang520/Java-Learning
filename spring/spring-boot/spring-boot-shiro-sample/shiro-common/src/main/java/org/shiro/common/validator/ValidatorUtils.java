package org.shiro.common.validator;


import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.shiro.common.exception.ShiroException;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午9:44:52
 * 类说明：hibernate-validator校验工具类
 */
public class ValidatorUtils {

  private static Validator validator;

  static {
    validator = Validation.buildDefaultValidatorFactory().getValidator();
  }

  /**
   * 校验对象
   *
   * @param object 待校验对象
   * @param groups 待校验的组
   * @throws RRException 校验不通过，则报ShiroException异常
   */
  public static void validateEntity(Object object, Class<?>... groups)
      throws ShiroException {
    Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
    if (!constraintViolations.isEmpty()) {
      ConstraintViolation<Object> constraint = (ConstraintViolation<Object>) constraintViolations.iterator().next();
      throw new ShiroException(constraint.getMessage());
    }
  }
}
