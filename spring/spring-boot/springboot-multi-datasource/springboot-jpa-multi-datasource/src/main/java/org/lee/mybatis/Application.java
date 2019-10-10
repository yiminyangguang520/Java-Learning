package org.lee.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author bruce
 */
@Configuration
@EnableTransactionManagement
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {Application.BASE_PACKAGE})
public class Application {

  final static String BASE_PACKAGE = "org/lee/mybatis";

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
