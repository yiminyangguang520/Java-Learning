package com.mkyong.common;

import java.util.Set;

/**
 * @author min
 */
public class Customer {

  private Set sets;

  public Set getSets() {
    return sets;
  }

  public void setSets(Set sets) {
    this.sets = sets;
  }

  @Override
  public String toString() {
    return "Customer [sets=" + sets + "]";
  }

}