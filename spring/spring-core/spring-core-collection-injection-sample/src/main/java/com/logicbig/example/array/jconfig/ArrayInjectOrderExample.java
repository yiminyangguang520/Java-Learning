package com.logicbig.example.array.jconfig;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author litz-a
 */
@Configuration
public class ArrayInjectOrderExample {

  @Bean
  public TestBean testBean() {
    return new TestBean();
  }

  @Bean
  @Order(3)
  public String refString() {
    return "my string 1";
  }

  @Bean
  @Order(1)
  public String refString2() {
    return "my string 2";
  }

  @Bean
  @Order(2)
  public String refString3() {
    return "my string 3";
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ArrayInjectOrderExample.class);

    TestBean bean = context.getBean(TestBean.class);
    System.out.println(Arrays.toString(bean.getStringArray()));
  }

  private static class TestBean {

    private String[] stringArray;

    @Autowired
    public void setStringArray(String[] stringArray) {
      this.stringArray = stringArray;
    }

    public String[] getStringArray() {
      return stringArray;
    }
  }
}