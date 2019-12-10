package com.lee.java8.concurrent.completablefuture.mahmoud;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.junit.Assert;

/**
 * @author litz-a
 */
public class CompletableFutureExamples {

  private static ExecutorService executor = Executors.newFixedThreadPool(3, new ThreadFactory() {
    int count = 1;

    @Override
    public Thread newThread(Runnable runnable) {
      return new Thread(runnable, "custom-executor-" + count++);
    }
  });

  private static Random random = new Random();

  public static void main(String[] args) {
    try {
      allOfAsyncExample();
    } finally {
      executor.shutdown();
    }
  }

  public static void completedFutureExample() {
    CompletableFuture<String> cf = CompletableFuture.completedFuture("message");
    Assert.assertTrue(cf.isDone());
    Assert.assertEquals("message", cf.getNow(null));
  }

  public static void completeExceptionallyExample() {
    CompletableFuture<String> cf = CompletableFuture.completedFuture("message")
        .thenApplyAsync(String::toUpperCase, CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));

    CompletableFuture<String> exceptionHandler = cf.handle((s, th) -> (th != null) ? "message upon cancel" : "");
    cf.completeExceptionally(new RuntimeException("completed exceptionally"));
    Assert.assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
    try {
      cf.join();
      Assert.fail("Should have thrown an exception");
    } catch (CompletionException ex) { // just for testing
      Assert.assertEquals("completed exceptionally", ex.getCause().getMessage());
    }

    Assert.assertEquals("message upon cancel", exceptionHandler.join());
  }

  public static void runAsyncExample() {
    CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
      Assert.assertTrue(Thread.currentThread().isDaemon());
      randomSleep();
    });
    Assert.assertFalse(cf.isDone());
    sleepEnough();
    Assert.assertTrue(cf.isDone());
  }

  public static void thenApplyExample() {
    CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApply(s -> {
      Assert.assertFalse(Thread.currentThread().isDaemon());
      return s.toUpperCase();
    });
    Assert.assertEquals("MESSAGE", cf.getNow(null));
  }

  public static void thenApplyAsyncExample() {
    CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
      Assert.assertTrue(Thread.currentThread().isDaemon());
      randomSleep();
      return s.toUpperCase();
    });
    Assert.assertNull(cf.getNow(null));
    Assert.assertEquals("MESSAGE", cf.join());
  }

  public static void thenApplyAsyncWithExecutorExample() {
    CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
      Assert.assertTrue(Thread.currentThread().getName().startsWith("custom-executor-"));
      Assert.assertFalse(Thread.currentThread().isDaemon());
      randomSleep();
      return s.toUpperCase();
    }, executor);

    Assert.assertNull(cf.getNow(null));
    Assert.assertEquals("MESSAGE", cf.join());
  }

  public static void thenAcceptExample() {
    StringBuilder result = new StringBuilder();
    CompletableFuture.completedFuture("thenAccept message").thenAccept(s -> result.append(s));
    Assert.assertTrue("Result was empty", result.length() > 0);
  }

  public static void thenAcceptAsyncExample() {
    StringBuilder result = new StringBuilder();
    CompletableFuture<Void> cf = CompletableFuture.completedFuture("thenAcceptAsync message")
        .thenAcceptAsync(s -> result.append(s));
    cf.join();
    Assert.assertTrue("Result was empty", result.length() > 0);
  }

  public static void cancelExample() {
    CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase,
        CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
    CompletableFuture<String> cf2 = cf.exceptionally(throwable -> "canceled message");
    Assert.assertTrue("Was not canceled", cf.cancel(true));
    Assert.assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
    Assert.assertEquals("canceled message", cf2.join());
  }

  public static void applyToEitherExample() {
    String original = "Message";
    CompletableFuture<String> cf1 = CompletableFuture.completedFuture(original)
        .thenApplyAsync(s -> delayedUpperCase(s));
    CompletableFuture<String> cf2 = cf1.applyToEither(
        CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
        s -> s + " from applyToEither");
    Assert.assertTrue(cf2.join().endsWith(" from applyToEither"));
  }

  public static void acceptEitherExample() {
    String original = "Message";
    StringBuilder result = new StringBuilder();
    CompletableFuture<Void> cf = CompletableFuture.completedFuture(original)
        .thenApplyAsync(s -> delayedUpperCase(s))
        .acceptEither(CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
            s -> result.append(s).append("acceptEither"));
    cf.join();
    Assert.assertTrue("Result was empty", result.toString().endsWith("acceptEither"));
  }

  public static void runAfterBothExample() {
    String original = "Message";
    StringBuilder result = new StringBuilder();
    CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).runAfterBoth(
        CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
        () -> result.append("done"));
    Assert.assertTrue("Result was empty", result.length() > 0);
  }

  public static void thenAcceptBothExample() {
    String original = "Message";
    StringBuilder result = new StringBuilder();
    CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).thenAcceptBoth(
        CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
        (s1, s2) -> result.append(s1 + s2));
    Assert.assertEquals("MESSAGEmessage", result.toString());
  }

  public static void thenCombineExample() {
    String original = "Message";
    CompletableFuture<String> cf = CompletableFuture.completedFuture(original).thenApply(s -> delayedUpperCase(s))
        .thenCombine(CompletableFuture.completedFuture(original).thenApply(s -> delayedLowerCase(s)),
            (s1, s2) -> s1 + s2);
    Assert.assertEquals("MESSAGEmessage", cf.getNow(null));
  }

  public static void thenCombineAsyncExample() {
    String original = "Message";
    CompletableFuture<String> cf = CompletableFuture.completedFuture(original)
        .thenApplyAsync(s -> delayedUpperCase(s))
        .thenCombine(CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
            (s1, s2) -> s1 + s2);
    Assert.assertEquals("MESSAGEmessage", cf.join());
  }

  public static void thenComposeExample() {
    String original = "Message";
    CompletableFuture<String> cf = CompletableFuture.completedFuture(original).thenApply(s -> delayedUpperCase(s))
        .thenCompose(upper -> CompletableFuture.completedFuture(original).thenApply(s -> delayedLowerCase(s))
            .thenApply(s -> upper + s));
    Assert.assertEquals("MESSAGEmessage", cf.join());
  }

  public static void anyOfExample() {
    StringBuilder result = new StringBuilder();
    List<String> messages = Arrays.asList("a", "b", "c");
    List<CompletableFuture<String>> futures = messages.stream()
        .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase(s)))
        .collect(Collectors.toList());
    CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((res, th) -> {
      if (th == null) {
        Assert.assertTrue(isUpperCase((String) res));
        result.append(res);
      }
    });
    Assert.assertTrue("Result was empty", result.length() > 0);
  }

  public static void allOfExample() {
    StringBuilder result = new StringBuilder();
    List<String> messages = Arrays.asList("a", "b", "c");
    List<CompletableFuture<String>> futures = messages.stream()
        .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase(s)))
        .collect(Collectors.toList());
    CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((v, th) -> {
      futures.forEach(cf -> Assert.assertTrue(isUpperCase(cf.getNow(null))));
      result.append("done");
    });
    Assert.assertTrue("Result was empty", result.length() > 0);
  }

  public static void allOfAsyncExample() {
    StringBuilder result = new StringBuilder();
    List<String> messages = Arrays.asList("a", "b", "c");
    List<CompletableFuture<String>> futures = messages.stream()
        .map(msg -> CompletableFuture.completedFuture(msg).thenApplyAsync(s -> delayedUpperCase(s)))
        .collect(Collectors.toList());
    CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
        .whenComplete((v, th) -> {
          futures.forEach(cf -> Assert.assertTrue(isUpperCase(cf.getNow(null))));
          result.append("done");
        });
    allOf.join();
    Assert.assertTrue("Result was empty", result.length() > 0);
  }

  private static boolean isUpperCase(String s) {
    for (int i = 0; i < s.length(); i++) {
      if (Character.isLowerCase(s.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  private static String delayedUpperCase(String s) {
    randomSleep();
    return s.toUpperCase();
  }

  private static String delayedLowerCase(String s) {
    randomSleep();
    return s.toLowerCase();
  }

  private static void randomSleep() {
    try {
      Thread.sleep(random.nextInt(1000));
    } catch (InterruptedException e) {
      // ...
    }
  }

  private static void sleepEnough() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      // ...
    }
  }

}