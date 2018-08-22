package com.logicbig.example.array.jconfig;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
@Configuration

@ComponentScan(basePackageClasses = ArrayInjectionScanExample.class,
    useDefaultFilters = false,
    includeFilters =
        {@ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            value = ArrayInjectionScanExample.TestBean.class)})
public class ArrayInjectionScanExample {

  @Bean
  public String[] strArray() {
    return new String[]{"two", "three", "four"};
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ArrayInjectionScanExample.class);
    TestBean bean = context.getBean(TestBean.class);
    System.out.println(Arrays.toString(bean.getStringArray()));
  }


  @Component
  static class TestBean {

    private final String[] stringArray;

    /**
     * Spring 4.3 doesn't require this annotation for constructor injection anymore
     * @param stringArray
     */
    @Autowired
    public TestBean(String[] stringArray) {
      this.stringArray = stringArray;
    }

    public String[] getStringArray() {
      return stringArray;
    }
  }
}