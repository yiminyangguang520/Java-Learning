package com.kfit;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
//其中@SpringBootApplication申明让spring boot自动给程序进行必要的配置，等价于以默认属性使用@Configuration，@EnableAutoConfiguration和@ComponentScan
@SpringBootApplication
@EnableAdminServer
public class App {

  /**
   * 参数里VM参数设置为： -javaagent:.\lib\springloaded-1.2.4.RELEASE.jar -noverify
   */
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
