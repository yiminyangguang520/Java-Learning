package com.hellowood.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author HelloWood
 */
@SpringBootApplication
public class SpringSecurityApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(SpringSecurityApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringSecurityApplication.class, args);
  }
}
