package com.logicbig.example.map.jconfig;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class MapInjectionExample {

  @Bean
  public TestBean testBean() {
    HashMap<String, Integer> map = new HashMap<>();
    map.put("one", 1);
    map.put("two", 2);
    return new TestBean(map);
  }

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(
            MapInjectionExample.class);
    TestBean bean = context.getBean(TestBean.class);
    System.out.println(bean.getMap());
  }

  private static class TestBean {

    private final Map<String, Integer> map;

    //@Autowired not required anymore, starting Spring 4.3
    private TestBean(Map<String, Integer> map) {
      this.map = map;
    }

    public Map<String, Integer> getMap() {
      return map;
    }
  }
}