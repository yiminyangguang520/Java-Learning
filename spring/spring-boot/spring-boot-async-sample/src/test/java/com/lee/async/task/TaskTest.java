package com.lee.async.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskTest {

  @Autowired
  private Task task;

  @Test
  public void testSyncTasks() throws Exception {
    task.doTaskOne();
    task.doTaskTwo();
    task.doTaskThree();
  }

}
