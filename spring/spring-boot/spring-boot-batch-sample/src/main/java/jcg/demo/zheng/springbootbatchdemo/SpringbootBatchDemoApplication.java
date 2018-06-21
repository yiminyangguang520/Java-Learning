package jcg.demo.zheng.springbootbatchdemo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author litz-a
 */
@SpringBootApplication
@EnableBatchProcessing
public class SpringbootBatchDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootBatchDemoApplication.class, args);
  }
}
