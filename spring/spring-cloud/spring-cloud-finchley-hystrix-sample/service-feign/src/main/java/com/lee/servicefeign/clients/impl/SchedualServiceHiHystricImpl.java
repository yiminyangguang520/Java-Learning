package com.lee.servicefeign.clients.impl;

import com.lee.servicefeign.clients.SchedualServiceHi;
import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
@Component
public class SchedualServiceHiHystricImpl implements SchedualServiceHi {

  @Override
  public String sayHiFromClientOne(String name) {
    return "sorry " + name;
  }
}