package com.logicbig.example.array.jconfig;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

/**
 * @author min
 */
@Configuration
@ComponentScan(basePackageClasses = ArrayQualifierInjectionScanExample.class,
    useDefaultFilters = false,
    includeFilters = {@ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        value = ArrayQualifierInjectionScanExample.TestBean.class)})
public class ArrayQualifierInjectionScanExample {

  @Bean
  public String[] strArray() {
    return new String[]{"two", "three", "four"};
  }

  @Bean(name = "myArray")
  public String[] strArray2() {
    return new String[]{"five", "six", "seven"};
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ArrayQualifierInjectionScanExample.class);
    TestBean bean = context.getBean(TestBean.class);
    System.out.println(Arrays.toString(bean.getStringArray()));
  }

  @Component
  static class TestBean {

    private final String[] stringArray;

    public TestBean(@Qualifier("myArray") String[] stringArray) {
      this.stringArray = stringArray;
    }

    public String[] getStringArray() {
      return stringArray;
    }
  }
}