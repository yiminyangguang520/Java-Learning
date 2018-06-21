package org.shiro.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:03:24
 * 类说明：springboot jar和war启动入口
 * MapperScan此处用扫描或者使用@Mapper注解在接口之上
 */
@SpringBootApplication
@ComponentScan("org.shiro")
@MapperScan(basePackages = {"org.shiro.security.modules.*.dao"})
@EnableSwagger2
public class AdminApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(AdminApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(AdminApplication.class);
  }

}
