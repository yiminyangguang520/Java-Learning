package com.itstyle.jwt.interceptor;

import com.alibaba.fastjson.JSON;
import com.itstyle.jwt.constant.Constant;
import com.itstyle.jwt.util.JwtUtils;
import com.itstyle.jwt.dto.ResponseModel;
import com.itstyle.jwt.dto.ResultStatus;
import com.itstyle.jwt.dto.ValidateResult;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 拦截器 用户权限校验
 *
 * @author litz-a
 */
@Slf4j
public class SysInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (handler instanceof HandlerMethod) {
      String authHeader = request.getHeader(Constant.TOKEN_HEADER);
      ValidateResult validateResult = JwtUtils.validateJWT(authHeader);
      if (validateResult.isSuccess()) {
        return true;
      } else {
        switch (validateResult.getErrCode()) {
          case Constant.JWT_ERRCODE_FAIL:
            log.info("签名验证不通过");
            print(response, ResponseModel.error(ResultStatus.TOKEN_NOT_PASS));
            break;
          case Constant.JWT_ERRCODE_EXPIRE:
            log.info("签名过期");
            print(response, ResponseModel.error(ResultStatus.TOKEN_EXPIRE));
            break;
          default:
            break;
        }
        return false;
      }
    }
    return true;
  }

  public void print(HttpServletResponse response, Object message) {
    try {
      response.setStatus(HttpStatus.OK.value());
      response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
      response.setHeader("Cache-Control", "no-cache, must-revalidate");
      PrintWriter writer = response.getWriter();
      writer.write(JSON.toJSONString(message));
      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {

  }

  /**
   * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。 该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行， 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
   */
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {

  }
}  