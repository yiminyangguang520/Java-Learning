package com.packtpub.springsecurity.web.configuration;

import javax.servlet.Filter;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * Registers the {@link DelegatingFilterProxy} to use the springSecurityFilterChain before any other registered {@link Filter}
 * @author litz-a
 */
@Order(1)
public class SecurityWebAppInitializer extends AbstractSecurityWebApplicationInitializer {

  /**
   * Don't initialize the filter directly, the Spring WebApplicationInitializer will take care of the initialization.
   */
  public SecurityWebAppInitializer() {
    super();
  }


  @Override
  public boolean enableHttpSessionEventPublisher() {
    return true;
  }

}
