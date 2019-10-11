package ns.client;

import ns.aop.LogMethodExecutionTime;
import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
@Component
public class TestClass {

  @LogMethodExecutionTime
  public void run() throws InterruptedException {
    Thread.sleep(3000);
  }

}
