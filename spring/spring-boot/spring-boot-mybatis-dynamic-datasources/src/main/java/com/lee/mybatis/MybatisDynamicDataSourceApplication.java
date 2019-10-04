package com.lee.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author bruce
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MybatisDynamicDataSourceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MybatisDynamicDataSourceApplication.class, args);
  }

}
