package org.ostenant.springboot.learning.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = Application.BASE_PACKAGE)
@EnableTransactionManagement
public class Application {

    final static String BASE_PACKAGE = "org.ostenant.springboot.learning.examples";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
