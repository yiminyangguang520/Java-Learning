package com.glodon.sso.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author litz-a
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GcrSsoServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(GcrSsoServerApplication.class, args);
  }

}