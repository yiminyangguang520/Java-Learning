package com.niraj.csvprocessor;

import org.springframework.batch.support.annotation.Classifier;

/**
 * @author min
 */
public class AggGroupClassifier {

  @Classifier
  public String classify(Person person) {
    return person.getAgeGroup();

  }
}
