package com.packtpub.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author min
 */
@SpringBootApplication(scanBasePackages = {
    "com.packtpub.springsecurity",
    "com.packtpub.springsecurity.configuration",
    "com.packtpub.springsecurity.domain",
    "com.packtpub.springsecurity.service",
    "com.packtpub.springsecurity.web",
    "com.packtpub.springsecurity.web.configuration",
})
@EntityScan("com.packtpub.springsecurity.domain")
public class CalendarApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(CalendarApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(CalendarApplication.class, args);
  }
}
