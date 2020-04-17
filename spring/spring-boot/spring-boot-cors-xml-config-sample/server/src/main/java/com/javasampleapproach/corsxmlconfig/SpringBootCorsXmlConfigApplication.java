package com.javasampleapproach.corsxmlconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author min
 */
@SpringBootApplication
@ImportResource("classpath:app-config.xml")
public class SpringBootCorsXmlConfigApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootCorsXmlConfigApplication.class, args);
  }
}
