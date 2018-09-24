package com.in28minutes.springboot.tutorial.basics.example.aop.business;

import com.in28minutes.springboot.tutorial.basics.example.aop.data.Dao2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 */
@Service
public class Business2 {

  @Autowired
  private Dao2 dao2;

  public String calculateSomething() {
    //Business Logic
    return dao2.retrieveSomething();
  }
}