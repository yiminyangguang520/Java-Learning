package com.boraji.tutorial.spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author min
 */
public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  /**
   * Load spring security configuration
   */
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] { WebSecurityConfig.class };
  }

  /**
   * Load spring web configuration
   * @return
   */
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class[] { WebConfig.class };
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] { "/" };
  }

}
