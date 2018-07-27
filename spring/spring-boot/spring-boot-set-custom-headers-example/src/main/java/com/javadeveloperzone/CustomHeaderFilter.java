package com.javadeveloperzone;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

/**
 *
 * @author JavaDeveloperZone
 * @date 16-12-2017
 */
@Component
public class CustomHeaderFilter implements Filter {

  @Override
  public void destroy() {
    System.out.println("destroy filter. release our resources here if any");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
      IOException, ServletException {
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    httpServletResponse.setHeader("Custom-Filter-Header", "Write Header using Filter");
    // continue execution of other filter chain.
    chain.doFilter(request, response);
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("Init filter");
  }

}
