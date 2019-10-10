package org.lee.mybatis;

import org.lee.mybatis.mapper.InstituteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author bruce
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  private InstituteMapper instituteMapper;

  @Autowired
  public void setInstituteMapper(InstituteMapper instituteMapper) {
    this.instituteMapper = instituteMapper;
  }

  @Override
  public void run(String... args) throws Exception {
  }
}
