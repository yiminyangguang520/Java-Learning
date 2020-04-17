package com.logicbig.example.collection.jconfig;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author min
 */
public class SetInjectionExample {

  @Bean
  public TestBean testBean() {
    return new TestBean(new HashSet(Arrays.asList("one hundred", "two hundred", "three hundred")));
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SetInjectionExample.class);
    TestBean bean = context.getBean(TestBean.class);
    System.out.println(bean.getStringSet());
  }

  private static class TestBean {

    private final Set<String> stringSet;

    private TestBean(Set<String> stringSet) {
      this.stringSet = stringSet;
    }

    public Set<String> getStringSet() {
      return stringSet;
    }
  }
}