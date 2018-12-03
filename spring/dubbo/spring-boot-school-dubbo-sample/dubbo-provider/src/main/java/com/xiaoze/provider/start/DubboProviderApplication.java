package com.xiaoze.provider.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * DubboProviderApplication
 *
 * @author xiaoze
 * @date 2018/6/11
 */
@ComponentScan(basePackages = "com.xiaoze.provider")
@SpringBootApplication
@MapperScan("com.xiaoze.provider.mapper")
@EnableTransactionManagement
public class DubboProviderApplication {

  public static void main(String[] args) {
    SpringApplication.run(DubboProviderApplication.class, args);
  }
}
