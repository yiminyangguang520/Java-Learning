package com.packtpub.springsecurity;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {
    "com.packtpub.springsecurity",
    "com.packtpub.springsecurity.configuration",
    "com.packtpub.springsecurity.dataaccess",
    "com.packtpub.springsecurity.domain",
    "com.packtpub.springsecurity.service",
    "com.packtpub.springsecurity.web",
    "com.packtpub.springsecurity.web.configuration"
}
)
@EntityScan("com.packtpub.springsecurity.domain")
public class CalendarApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(CalendarApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(CalendarApplication.class, args);
  }

  //    @Profile("trace")
  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {

      System.out.println("Let's inspect the beans provided by Spring Boot:\n");

      String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);
      for (String beanName : beanNames) {
        System.out.println(beanName);
      }

      System.out.println("---");
    };
  }

} // The End...
