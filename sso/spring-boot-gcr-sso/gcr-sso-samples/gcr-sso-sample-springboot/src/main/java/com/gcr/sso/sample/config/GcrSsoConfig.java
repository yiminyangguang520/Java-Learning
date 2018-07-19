package com.gcr.sso.sample.config;

import com.glodon.sso.core.constant.Constant;
import com.glodon.sso.core.filter.GcrSsoFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author litz-a
 */
@Configuration
public class GcrSsoConfig {

  @Value("${xxl.sso.server}")
  private String gcrSsoServer;

  @Value("${xxl.sso.logout.path}")
  private String gcrSsoLogoutPath;

  @Bean
  public FilterRegistrationBean xxlSsoFilterRegistration() {
    FilterRegistrationBean registration = new FilterRegistrationBean();

    registration.setName("GcrSsoFilter");
    registration.setOrder(1);
    registration.addUrlPatterns("/*");
    registration.setFilter(new GcrSsoFilter());
    registration.addInitParameter(Constant.SSO_SERVER, gcrSsoServer);
    registration.addInitParameter(Constant.SSO_LOGOUT_PATH, gcrSsoLogoutPath);

    return registration;
  }

}
