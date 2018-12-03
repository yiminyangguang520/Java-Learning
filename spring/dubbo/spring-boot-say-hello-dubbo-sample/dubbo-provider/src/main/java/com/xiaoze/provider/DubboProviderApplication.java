package com.xiaoze.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author litz-a
 */
@SpringBootApplication
public class DubboProviderApplication {

  public static void main(String[] args) {
    //由于dubbo提供者只是单纯提供服务的 可以为一个非web环境
    new SpringApplicationBuilder(DubboProviderApplication.class).web(WebApplicationType.NONE).run(args);
    SpringApplication.run(DubboProviderApplication.class, args);
  }
}
