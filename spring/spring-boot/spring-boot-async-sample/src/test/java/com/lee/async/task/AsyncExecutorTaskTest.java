package com.lee.async.task;

import static java.lang.Thread.sleep;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncExecutorTaskTest {

  @Autowired
  private AsyncExecutorTask task;

  @Test
  public void testAsyncExecutorTask() throws Exception {
    task.doTaskOne();
    task.doTaskTwo();
    task.doTaskThree();

    sleep(30 * 1000L);
  }
}
