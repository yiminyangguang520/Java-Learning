package com.mkyong.hello.impl;

import com.mkyong.hello.HelloWorld;

/**
 * @author litz-a
 */
public class HelloWorldImpl implements HelloWorld {

  public void printHelloWorld(String msg) {
    System.out.println("Hello : " + msg);
  }

}