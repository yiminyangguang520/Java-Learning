package com.mkyong.output;

/**
 * @author litz-a
 */
public class OutputHelper {

  private IOutputGenerator outputGenerator;

  public void generateOutput() {
    outputGenerator.generateOutput();
  }

  /**
   * DI via constructor
   * @param outputGenerator 输出生成
   */
  public OutputHelper(IOutputGenerator outputGenerator) {
    this.outputGenerator = outputGenerator;
  }

}