package com.mkyong.output.impl;

import com.mkyong.output.IOutputGenerator;

/**
 * @author litz-a
 */
public class CsvOutputGenerator implements IOutputGenerator {

  public void generateOutput() {
    System.out.println("This is Csv Output Generator");
  }
}