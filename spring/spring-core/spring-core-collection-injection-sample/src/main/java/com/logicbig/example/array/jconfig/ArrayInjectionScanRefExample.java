package com.logicbig.example.array.jconfig;


import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
@PropertySource("classpath:app-props.properties")
@Configuration
@ComponentScan(basePackageClasses = ArrayInjectionScanRefExample.class, useDefaultFilters = false,
    includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        value = ArrayInjectionScanRefExample.class))

public class ArrayInjectionScanRefExample {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ArrayInjectionScanRefExample.class);

    TestBean bean = context.getBean(TestBean.class);
    System.out.println(Arrays.toString(bean.getRefBeanServices()));
  }

  @Component
  static class TestBean {

    private final RefBeanService[] refBeanServices;

    /**
     * this annotation not needed starting spring 4.3
     * @param refBean
     */
    @Autowired
    private TestBean(RefBeanService[] refBean) {
      this.refBeanServices = refBean;
    }

    public RefBeanService[] getRefBeanServices() {
      return refBeanServices;
    }
  }

  private interface RefBeanService {

    /**
     * getStr
     * @return
     */
    String getStr();
  }

  @Component
  private static class RefBean1 implements RefBeanService {

    private String str;

    @Override
    public String getStr() {
      return str;
    }

    @Value("${some-prop1:defaultStr}")
    public void setStr(String str) {
      this.str = str;
    }

    @Override
    public String toString() {
      return "RefBean{" +
          "str='" + str + '\'' +
          '}';
    }
  }

  @Component
  private static class RefBean2 implements RefBeanService {

    private String str;

    @Override
    public String getStr() {
      return str;
    }

    @Value("${some-prop2:defaultStr}")
    public void setStr(String str) {
      this.str = str;
    }

    @Override
    public String toString() {
      return "RefBean{" +
          "str='" + str + '\'' +
          '}';
    }
  }
}