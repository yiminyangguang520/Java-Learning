package com.lee.async.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncTaskTest {

  @Autowired
  private AsyncTask task;

  @Test
  public void testAsyncTasks() throws Exception {
    task.doTaskOne();
    task.doTaskTwo();
    task.doTaskThree();
  }

}
