package com.memorynotfound.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author litz-a
 */
public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[]{WebConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return null;
  }

}
