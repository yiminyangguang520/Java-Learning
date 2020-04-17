package com.niraj.csvprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author min
 */
public class AgeProcessor implements ItemProcessor<Person, Person> {

  private static final Logger log = LoggerFactory.getLogger(AgeProcessor.class);

  @Override
  public Person process(Person person) throws Exception {

    if (person.getAge() <= 18) {
      person.setAgeGroup("TEENAGER");
    } else if (person.getAge() > 18 && person.getAge() <= 60) {
      person.setAgeGroup("YOUNGADULT");
    } else if (person.getAge() > 60) {
      person.setAgeGroup("SENIORCITIZENS");
    }
    log.info("Persion {}", person);
    return person;
  }

}
