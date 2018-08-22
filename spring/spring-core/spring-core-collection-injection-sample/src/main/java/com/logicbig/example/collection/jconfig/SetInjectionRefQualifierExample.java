package com.logicbig.example.collection.jconfig;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author litz-a
 */
@PropertySource("classpath:app-props.properties")
@Configuration
public class SetInjectionRefQualifierExample {

  @Bean
  public TestBean testBean() {
    return new TestBean();
  }

  @Bean
  @Qualifier("myRefBean")
  public RefBeanService refBean1() {
    return new RefBean();
  }

  @Bean
  public RefBeanService refBean2() {
    return new RefBean2();
  }

  @Bean
  @Qualifier("myRefBean")
  public RefBeanService refBean3() {
    return new RefBean3();
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SetInjectionRefQualifierExample.class);

    TestBean bean = context.getBean(TestBean.class);
    System.out.println(bean.getRefBeanServices());
  }

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

  public interface RefBeanService {

    /**
     * getStr
     * @return
     */
    String getStr();
  }

  public static class RefBean implements RefBeanService {

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
      return "RefBean{" +
          "str='" + str + '\'' +
          '}';
    }
  }

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
      return "RefBean{" +
          "str='" + str + '\'' +
          '}';
    }
  }
}