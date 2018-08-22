package com.logicbig.example.map.jconfig;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapRefInjection {

  @Bean
  public TestBean testBean() {
    return new TestBean();
  }

  @Bean
  public RefBean refBean() {
    return new RefBean("one");
  }

  @Bean
  public RefBean refBean2() {
    return new RefBean("two");
  }


  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(MapRefInjection.class);
    TestBean bean = context.getBean(TestBean.class);
    System.out.println(bean.getMap());
  }

  private static class TestBean {

    private Map<String, RefBean> map;

    /**
     * An autowired Maps values will consist of all bean instances that match the expected type, and the Maps keys will contain the corresponding bean names.
     */
    @Autowired
    public void setMap(Map<String, RefBean> map) {
      this.map = map;
    }

    public Map<String, RefBean> getMap() {
      return map;
    }
  }

  private static class RefBean {

    private final String str;

    private RefBean(String str) {
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