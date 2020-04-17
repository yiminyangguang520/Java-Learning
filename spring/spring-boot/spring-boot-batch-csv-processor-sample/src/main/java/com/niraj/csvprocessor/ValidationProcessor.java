package com.niraj.csvprocessor;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author min
 */
public class ValidationProcessor implements ItemProcessor<Person, Person> {

  private static final Logger log = LoggerFactory.getLogger(ValidationProcessor.class);

  private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Override
  public Person process(final Person person) throws Exception {

    Set<ConstraintViolation<Person>> validate = validator.validate(person);

    if (!validate.isEmpty()) {
      log.error("Business Validation failed for  {}", person);
      log.error(validate.toString());
      return null;

    }

    return person;
  }

}
