package com.brahalla.cerberus.integration.config;

import com.brahalla.cerberus.integration.util.TestApiConfig;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestContext {

  @Bean
  public TomcatServletWebServerFactory  servletContainer() {
    TomcatServletWebServerFactory  factory = new TomcatServletWebServerFactory();
    factory.setPort(TestApiConfig.PORT);
    //factory.setSessionTimeout(10, TimeUnit.MINUTES);
    return factory;
  }

}
