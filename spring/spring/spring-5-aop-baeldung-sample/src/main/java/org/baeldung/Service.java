package org.baeldung;

import org.springframework.stereotype.Component;

/**
 * @author min
 */
@Component
public class Service {

  @LogExecutionTime
  public void serve() throws InterruptedException {
    Thread.sleep(2000);
  }
}
