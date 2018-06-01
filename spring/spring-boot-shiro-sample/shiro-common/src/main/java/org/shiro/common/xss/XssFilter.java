package org.shiro.common.xss;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午9:49:03
 * 类说明：XSS过滤
 */
public class XssFilter implements Filter {

  @Override
  public void init(FilterConfig config) throws ServletException {
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
        (HttpServletRequest) request);
    chain.doFilter(xssRequest, response);
  }

  @Override
  public void destroy() {
  }

}