package com.logicbig.example.array.jconfig;

import java.util.Arrays;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author min
 */
@Configuration
public class ArrayInjectionExample {

  @Bean
  public TestBean testBean() {
    return new TestBean(new String[]{"one", "two", "three"});
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ArrayInjectionExample.class);

    TestBean bean = context.getBean(TestBean.class);
    System.out.println(Arrays.toString(bean.getStringArray()));
  }

  private static class TestBean {

    private final String[] stringArray;

    public TestBean(String[] stringArray) {
      this.stringArray = stringArray;
    }

    public String[] getStringArray() {
      return stringArray;
    }
  }
}