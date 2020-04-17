package com.mkyong.output;

/**
 * @author min
 */
public class OutputHelper {

  private IOutputGenerator outputGenerator;

  public void generateOutput() {
    outputGenerator.generateOutput();
  }

  /**
   * DI via setter method
   * @param outputGenerator param
   */
  public void setOutputGenerator(IOutputGenerator outputGenerator) {
    this.outputGenerator = outputGenerator;
  }
}