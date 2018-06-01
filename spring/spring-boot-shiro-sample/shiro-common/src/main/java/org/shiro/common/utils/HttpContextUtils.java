package org.shiro.common.utils;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午9:41:39
 * 类说明：HttpContext工具类
 */
public class HttpContextUtils {

  public static HttpServletRequest getHttpServletRequest() {
    return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
  }
}
