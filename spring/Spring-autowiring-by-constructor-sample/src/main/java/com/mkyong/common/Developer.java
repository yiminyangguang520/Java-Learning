package com.mkyong.common;

/**
 * @author litz-a
 */
public class Developer {

  private Language language;

  public Developer(Language language) {
    this.language = language;
  }

  @Override
  public String toString() {
    return "Developer [language=" + language + "]";
  }

}