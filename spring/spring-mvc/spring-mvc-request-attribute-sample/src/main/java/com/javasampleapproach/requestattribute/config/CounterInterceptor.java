package com.javasampleapproach.requestattribute.config;

import com.javasampleapproach.requestattribute.model.Counter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author min
 */
@Component
public class CounterInterceptor implements HandlerInterceptor {

  private static final int START_COUNT = 9;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (request.getAttribute("counter") == null) {
      request.setAttribute("counter", new Counter(START_COUNT));
      System.out.println("Set Counter Attribute by value: " + START_COUNT);
    }
    return true;
  }
}
