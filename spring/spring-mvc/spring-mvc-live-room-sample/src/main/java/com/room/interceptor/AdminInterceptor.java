package com.room.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author min
 * Created by Doublestar on 2017/12/28 22:00).
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {

  /**
   * Handler执行之前调用这个方法
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) throws Exception {
    //获取Session
    HttpSession session = request.getSession();
    String username = (String) session.getAttribute("name");

    if ("admin".equals(username)) {
      return true;
    }
    //不符合条件的，跳转到登录界面
    response.sendRedirect("/home/index");
    return false;
  }
}
