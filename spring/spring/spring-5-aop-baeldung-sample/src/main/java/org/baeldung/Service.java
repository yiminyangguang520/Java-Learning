package org.baeldung;

import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
@Component
public class Service {

  @LogExecutionTime
  public void serve() throws InterruptedException {
    Thread.sleep(2000);
  }
}
