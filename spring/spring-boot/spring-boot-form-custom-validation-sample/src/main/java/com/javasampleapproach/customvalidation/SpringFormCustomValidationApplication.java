package com.javasampleapproach.customvalidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringFormCustomValidationApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(SpringFormCustomValidationApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringFormCustomValidationApplication.class, args);
  }
}
