/**
 *
 */
package com.devglan.model;

import java.util.Arrays;

/**
 * @author only2dhir
 */
public enum QuestionCategory {

  CSE("cse"), ECE("ece");

  private String value;

  QuestionCategory(String value) {
    this.value = value;
  }

  public static QuestionCategory fromValue(String value) {
    for (QuestionCategory category : values()) {
      if (category.value.equalsIgnoreCase(value)) {
        return category;
      }
    }
    throw new IllegalArgumentException("Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
  }
}
