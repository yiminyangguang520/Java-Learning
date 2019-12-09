package com.lee.java8;

/**
 * @author bruce
 */
public class DefaultInAction {

  public static void main(String[] args) {
    C c = new C();
    c.hello();
  }


  private interface A {

    default void hello() {
      System.out.println("==A.hello==");
    }
  }

  private interface B {

    default void hello() {
      System.out.println("==B.hello==");
    }
  }

  private static class C implements B, A {

    @Override
    public void hello() {
      B.super.hello();
    }
  }
}
