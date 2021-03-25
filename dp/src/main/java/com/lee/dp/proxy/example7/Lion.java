package com.lee.dp.proxy.example7;

/**
 * @author litz-a
 */
public class Lion implements Cat {

  private String name;
  private int runningSpeed;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getRunningSpeed() {
    return runningSpeed;
  }

  public void setRunningSpeed(int runningSpeed) {
    this.runningSpeed = runningSpeed;
  }

  public Lion() {
  }

  @Override
  public String eatFood(String foodName) {
    String eat = this.name + " Lion eat food. foodName = " + foodName;
    System.out.println(eat);
    return eat;
  }

  @Override
  public boolean running() {
    System.out.println(this.name + " Lion is running . Speed :" + this.runningSpeed);
    return false;
  }
}
