package com.lee.java8.concurrent.completablefuture;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author bruce
 */
public class CompletableFutureApplyAscAndAcceptAsync {

  public int findAccountNumber() {
    System.out.println(Thread.currentThread() + " findAccountNumber");
    sleep(1);
    return 10;
  }

  public int calculateBalance(int accountNumber) {
    System.out.println(Thread.currentThread() + " calculateBalance");
    sleep(1);
    return accountNumber * accountNumber;
  }

  public Integer notifyBalance(Integer balance) {
    System.out.println(Thread.currentThread() + "Sending Notification");
    sleep(1);
    return balance;
  }

  private void sleep(int seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void notify(String str) {
    sleep(1);
    System.out.println(Thread.currentThread() + "Recived vlaue " + str);
  }

  @Test
  public void completableFutureApplyAsync() {
    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
    ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    CompletableFuture<Integer> completableFuture =
        CompletableFuture.supplyAsync(this::findAccountNumber, newFixedThreadPool)
            .thenApplyAsync(this::calculateBalance, newSingleThreadScheduledExecutor)
            .thenApplyAsync(this::notifyBalance);
    Integer balance = completableFuture.join();
    Assertions.assertEquals(Integer.valueOf(balance), Integer.valueOf(100));

  }

}
