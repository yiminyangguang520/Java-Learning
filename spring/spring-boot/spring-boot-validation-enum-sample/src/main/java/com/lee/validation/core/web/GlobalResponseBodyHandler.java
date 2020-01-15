package com.lee.validation.core.web;

import com.lee.validation.core.vo.CommonResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 只拦截我们的 Controller 所在包，避免其它类似 swagger 提供的 API 被切面拦截
 * @author litz-a
 */
@ControllerAdvice(basePackages = "cn.iocoder.springboot.lab22.validation.controller")
public class GlobalResponseBodyHandler implements ResponseBodyAdvice {

  @Override
  public boolean supports(MethodParameter returnType, Class converterType) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType,
      ServerHttpRequest request, ServerHttpResponse response) {
    // 如果已经是 CommonResult 类型，则直接返回
    if (body instanceof CommonResult) {
      return body;
    }
    // 如果不是，则包装成 CommonResult 类型
    return CommonResult.success(body);
  }

}
