package com.mkyong.common;

/**
 * @author min
 */
public class Panda {

  private KungFu kungfu;

  /*
  public Panda(KungFu kungfu) {
    System.out.println("autowiring by constructor");
    this.kungfu = kungfu;
  }*/

  /*
  public Panda(String kungfu) {
    System.out.println("autowiring by constructor");
  }*/

  public KungFu getKungfu() {
    return kungfu;
  }

  public void setKungfu(KungFu kungfu) {
    System.out.println("autowiring by type");
    this.kungfu = kungfu;
  }

  @Override
  public String toString() {
    return "Person [kungfu=" + kungfu + "]";
  }

}