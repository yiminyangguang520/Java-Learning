package com.example.lee.bean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author min
 */
@Configuration
public class CollectionConfig {

  @Bean
  public CollectionsBean getCollectionsBean() {
    return new CollectionsBean(new HashSet<>(Arrays.asList("John", "Adam", "Harry")));
  }

  @Bean
  public List<String> nameList() {
    return Arrays.asList("John", "Adam", "Harry", null);
  }

  @Bean
  public Map<Integer, String> nameMap() {
    Map<Integer, String> nameMap = new HashMap<>(3);
    nameMap.put(1, "John");
    nameMap.put(2, "Adam");
    nameMap.put(3, "Harry");
    return nameMap;
  }

  @Bean
  @Qualifier("CollectionsBean")
  @Order(2)
  public BaeldungBean getElement() {
    return new BaeldungBean("John");
  }

  @Bean
  @Order(3)
  public BaeldungBean getAnotherElement() {
    return new BaeldungBean("Adam");
  }

  @Bean
  @Order(1)
  public BaeldungBean getOneMoreElement() {
    return new BaeldungBean("Harry");
  }
}
