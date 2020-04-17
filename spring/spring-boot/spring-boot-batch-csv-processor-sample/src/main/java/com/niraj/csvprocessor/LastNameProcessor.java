package com.niraj.csvprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author min
 */
public class LastNameProcessor implements ItemProcessor<Person, Person> {

  private static final Logger log = LoggerFactory.getLogger(LastNameProcessor.class);

  @Override
  public Person process(final Person person) throws Exception {
    String lastName = person.getLastName().toUpperCase();
    person.setLastName(lastName);
    log.debug("Persion {}", person);

    return person;
  }

}
