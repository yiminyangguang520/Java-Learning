package org.baeldung.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author min
 */
public class AdderAfterAspect {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public void afterAdvice() throws Throwable {
    logger.info("I'm done calling the method");
  }
}
