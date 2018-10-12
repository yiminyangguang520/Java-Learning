package com.logicbig.example.web;

import com.logicbig.example.config.AppConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author litz-a
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  /**
   * 指定根配置类,可用来组合各种配置文件,ContextLoderListener
   * @return
   */
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[]{AppConfig.class};
  }

  /**
   * 指定web配置类,DispatcherServlet
   * @return
   */
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return null;
  }

  /**
   * 将DispatcherServlet映射到"/"
   * @return
   */
  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }
}