package com.niraj.csvprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author litz-a
 */
public class FirstNameProcessor implements ItemProcessor<Person, Person> {

  private static final Logger log = LoggerFactory.getLogger(FirstNameProcessor.class);

  @Override
  public Person process(Person person) throws Exception {
    final String firstName = person.getFirstName().toUpperCase();
    person.setFirstName(firstName);
    log.debug("Persion {}", person);
    return person;
  }

}
