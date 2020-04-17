package com.javasampleapproach.injectcollections.model;

/**
 * @author min
 */
public class Bilingual {

  private String firstLanguage;
  private String secondLanguage;

  public String getFirstLanguage() {
    return firstLanguage;
  }

  public void setFirstLanguage(String firstLanguage) {
    this.firstLanguage = firstLanguage;
  }

  public String getSecondLanguage() {
    return secondLanguage;
  }

  public void setSecondLanguage(String secondLanguage) {
    this.secondLanguage = secondLanguage;
  }

  @Override
  public String toString() {
    return "[1st Language: " + firstLanguage + " , 2nd language: " + secondLanguage + "]";
  }
}
