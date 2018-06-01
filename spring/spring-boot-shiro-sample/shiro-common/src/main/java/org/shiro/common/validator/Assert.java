package org.shiro.common.validator;

import org.apache.commons.lang.StringUtils;
import org.shiro.common.exception.ShiroException;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午9:43:12
 * 类说明：数据校验
 */
public abstract class Assert {

  public static void isBlank(String str, String message) {
    if (StringUtils.isBlank(str)) {
      throw new ShiroException(message);
    }
  }

  public static void isNull(Object object, String message) {
    if (object == null) {
      throw new ShiroException(message);
    }
  }
}
