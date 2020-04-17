package com.boraji.tutorial.spring.config;

import org.h2.server.web.WebServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author min
 */
public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  /**
   * Load database and spring security configuration
   * @return
   */
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class[] { AppConfig.class, WebSecurityConfig.class };
  }

  /**
   * Load database and spring web configuration
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

  /**
   * reference https://sanaulla.info/2017/11/19/configure-embedded-h2-console-with-spring-mvc-application/
   * @param servletContext
   * @throws ServletException
   */
  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    super.onStartup(servletContext);
    ServletRegistration.Dynamic servlet = servletContext.addServlet("h2-console", new WebServlet());
    servlet.setLoadOnStartup(2);
    servlet.addMapping("/console/**");
  }
}
