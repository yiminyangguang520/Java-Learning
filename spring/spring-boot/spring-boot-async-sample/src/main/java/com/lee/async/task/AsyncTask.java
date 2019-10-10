package com.lee.async.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author bruce
 */
@Component
public class AsyncTask extends AbstractTask {

  @Override
  @Async
  public void doTaskOne() throws Exception {
    super.doTaskOne();
  }

  @Override
  @Async
  public void doTaskTwo() throws Exception {
    super.doTaskTwo();
  }

  @Override
  @Async
  public void doTaskThree() throws Exception {
    super.doTaskThree();
  }
}
