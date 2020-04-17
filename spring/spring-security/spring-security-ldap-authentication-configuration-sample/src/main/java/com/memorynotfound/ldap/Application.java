package com.memorynotfound.ldap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author min
 */
@SpringBootApplication
// Enable if you want to use Spring Security + LDAP Authentication XML Configuration
//@EnableWebSecurity
//@ImportResource("classpath:spring-security-config.xml")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
