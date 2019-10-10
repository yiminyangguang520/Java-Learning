package com.lee.async.task;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.lang.Thread.sleep;

import java.util.concurrent.Future;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncCallBackTaskTest {

  @Autowired
  private AsyncCallBackTask task;

  @Test
  public void testAsyncCallbackTask() throws Exception {
    long start = currentTimeMillis();
    Future<String> task1 = task.doTaskOneCallback();
    Future<String> task2 = task.doTaskTwoCallback();
    Future<String> task3 = task.doTaskThreeCallback();

    // 三个任务都调用完成，退出循环等待
    while (!task1.isDone() || !task2.isDone() || !task3.isDone()) {
      sleep(1000);
    }

    long end = currentTimeMillis();
    out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
  }
}
