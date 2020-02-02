package com.lee.conditiononclass;

import com.lee.conditiononclass.player.Bruce;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author bruce
 */
@SpringBootApplication
public class SpringBootConditionOnClassSampleApplication implements CommandLineRunner {

  private Bruce bruce;

  @Autowired
  public void setBruce(@Qualifier("bananaVan") Bruce bruce) {
    this.bruce = bruce;
  }

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(SpringBootConditionOnClassSampleApplication.class, args);
    Arrays.asList(context.getBeanDefinitionNames()).forEach(System.out::println);
  }

  @Override
  public void run(String... args) {
    bruce.fight();
  }

}
