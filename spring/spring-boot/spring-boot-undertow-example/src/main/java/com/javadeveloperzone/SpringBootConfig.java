package com.javadeveloperzone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author JavaDeveloperZone
 * @date 19-07-2017
 * @ComponentScan // Using a root package also allows the @ComponentScan annotation to be used without needing to specify a basePackage attribute
 */
@SpringBootApplication
@ComponentScan
public class SpringBootConfig {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(SpringBootConfig.class, args);
  }
}
