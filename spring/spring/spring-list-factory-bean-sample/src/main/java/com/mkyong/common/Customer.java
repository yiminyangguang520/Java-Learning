package com.mkyong.common;

import java.util.List;

/**
 * @author min
 */
public class Customer {

  private List lists;

  public List getLists() {
    return lists;
  }

  public void setLists(List lists) {
    this.lists = lists;
  }

  @Override
  public String toString() {
    return "Customer [lists=" + lists + "]";
  }


}