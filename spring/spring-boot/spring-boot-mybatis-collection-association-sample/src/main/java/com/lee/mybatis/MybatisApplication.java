package com.lee.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author min
 */
@SpringBootApplication
@MapperScan("com.lee.mybatis.dao")
public class MybatisApplication {

  public static void main(String[] args) {
    SpringApplication.run(MybatisApplication.class, args);
  }
}
