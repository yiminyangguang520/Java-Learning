package com.logicbig.example.array.jconfig;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author litz-a
 */
@Configuration
public class ArrayPropInjectionExample {

  @Bean
  public TestBean testBean() {
    return new TestBean();
  }

  @Bean
  public String[] strArray() {
    return new String[]{"two", "three", "four"};
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ArrayPropInjectionExample.class);

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