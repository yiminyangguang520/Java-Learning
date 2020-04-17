package org.websparrow.beans;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author min
 */
public class DefaultType {

  private List<String> fruits;
  private Set<String> cricketers;
  private Map<String, String> countryCapital;

  public void setFruits(List<String> fruits) {
    this.fruits = fruits;
  }

  public void setCricketers(Set<String> cricketers) {
    this.cricketers = cricketers;
  }

  public void setCountryCapital(Map<String, String> countryCapital) {
    this.countryCapital = countryCapital;
  }


  public void display() {

    System.out.println("Fruits...." + "");

    for (String fruit : fruits) {
      System.out.println(fruit);
    }

    System.out.println("\nCricketers...." + "");

    for (String cricketer : cricketers) {
      System.out.println(cricketer);
    }

    System.out.println("\nCountry and Capital...." + "");

    Set<String> keys = countryCapital.keySet();
    for (String key : keys) {
      System.out.println(key + " : " + countryCapital.get(key));
    }

  }
}
