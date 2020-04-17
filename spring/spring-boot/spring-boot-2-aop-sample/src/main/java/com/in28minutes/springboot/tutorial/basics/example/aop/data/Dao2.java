package com.in28minutes.springboot.tutorial.basics.example.aop.data;

import org.springframework.stereotype.Repository;

/**
 * @author min
 */
@Repository
public class Dao2 {

  public String retrieveSomething() {
    return "Dao2";
  }

}