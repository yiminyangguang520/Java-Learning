package cn.lqdev.learning.springboot.chapter35;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybaits集成
 *
 * @author litz-a
 */
@SpringBootApplication
@Slf4j
public class MybatisApplication {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(MybatisApplication.class, args);
    log.info("spring-boot-mybatis-chapter35启动!");
  }
}
