package com.javasampleapproach.requestattribute.model;

/**
 * @author litz-a
 */
public class Counter {

  private int count;

  public Counter(int count) {
    this.count = count;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  @Override
  public String toString() {
    return "Counter [count=" + count + "]";
  }

}
