package cn.com.hellowood.rocketmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author litz-a
 */
@SpringBootApplication
public class RocketMQServiceApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(RocketMQServiceApplication.class);
  }


  public static void main(String[] args) {
    SpringApplication.run(RocketMQServiceApplication.class, args);
  }
}
