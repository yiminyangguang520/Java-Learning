package com.logicbig.example.collection.jconfig;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:app-props.properties")
@Configuration
@ComponentScan(basePackageClasses = SetInjectionScanRefQualifierExample.class,
    useDefaultFilters = false,
    includeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        value = {SetInjectionScanRefQualifierExample.class}))

public class SetInjectionScanRefQualifierExample {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(
            SetInjectionScanRefQualifierExample.class);

    TestBean bean = context.getBean(TestBean.class);
    System.out.println(bean.getRefBeanServices());
  }

  @Component
  public static class TestBean {

    private Set<RefBeanService> refBeanServices;

    @Autowired
    @Qualifier("myRefBean")
    public void setRefBeanServices(Set<RefBeanService> refBeanServices) {
      this.refBeanServices = refBeanServices;
    }

    public Set<RefBeanService> getRefBeanServices() {
      return refBeanServices;
    }
  }

  public static interface RefBeanService {

    String getStr();
  }

  @Component
  @Qualifier("myRefBean")
  public static class RefBean implements RefBeanService {

    private String str;

    public String getStr() {
      return str;
    }

    @Value("${some-prop1:defaultStr}")
    public void setStr(String str) {
      this.str = str;
    }

    @Override
    public String toString() {
      return "RefBean{" + "str='" + str + '\'' + '}';
    }
  }

  @Component
  public static class RefBean2 implements RefBeanService {

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
      return "RefBean{str='" + str + "'}";
    }
  }

  @Component
  @Qualifier("myRefBean")
  public static class RefBean3 implements RefBeanService {

    private String str;

    @Override
    public String getStr() {
      return str;
    }

    @Value("${some-prop3:defaultStr}")
    public void setStr(String str) {
      this.str = str;
    }

    @Override
    public String toString() {
      return "RefBean{ str='" + str + "'}";
    }
  }
}