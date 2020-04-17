package com.logicbig.example.web;

import com.logicbig.example.config.AppConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author min
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  /**
   * ָ����������,��������ϸ��������ļ�,ContextLoderListener
   * @return
   */
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[]{AppConfig.class};
  }

  /**
   * ָ��web������,DispatcherServlet
   * @return
   */
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return null;
  }

  /**
   * ��DispatcherServletӳ�䵽"/"
   * @return
   */
  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }
}