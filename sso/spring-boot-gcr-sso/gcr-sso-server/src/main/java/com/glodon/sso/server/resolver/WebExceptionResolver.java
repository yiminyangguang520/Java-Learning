package com.glodon.sso.server.resolver;

import com.glodon.sso.core.entity.SsoResponse;
import com.glodon.sso.core.exception.GcrSsoException;
import com.glodon.sso.core.util.JacksonUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统一异常处理（Controller切面方式实现）
 *
 * 1、@ControllerAdvice：扫描所有Controller
 * 2、@ControllerAdvice(annotations=RestController.class)：扫描指定注解类型的Controller
 * 3、@ControllerAdvice(basePackages={"com.aaa","com.bbb"})：扫描指定package下的Controller
 *
 * @author litz-a
 */
@Component
public class WebExceptionResolver implements HandlerExceptionResolver {

  private static transient Logger logger = LoggerFactory.getLogger(WebExceptionResolver.class);

  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    logger.error("WebExceptionResolver:{}", ex);

    // if json
    boolean isJson = false;
    HandlerMethod method = (HandlerMethod) handler;
    ResponseBody responseBody = method.getMethodAnnotation(ResponseBody.class);
    if (responseBody != null) {
      isJson = true;
    }

    // error result
    SsoResponse<String> errorResult;
    if (ex instanceof GcrSsoException) {
      errorResult = new SsoResponse<>(SsoResponse.FAIL_CODE, ex.getMessage());
    } else {
      errorResult = new SsoResponse<>(SsoResponse.FAIL_CODE, ex.toString().replaceAll("\n", "<br/>"));
    }

    // response
    ModelAndView mv = new ModelAndView();
    if (isJson) {
      try {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(JacksonUtil.writeValueAsString(errorResult));
      } catch (IOException e) {
        logger.error(e.getMessage(), e);
      }
      return mv;
    } else {
      mv.addObject("exceptionMsg", errorResult.getMessage());
      mv.setViewName("/common/common.exception");
      return mv;
    }
  }

}