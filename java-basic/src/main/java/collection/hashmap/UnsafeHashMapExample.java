package collection.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author bruce
 */
public class UnsafeHashMapExample {

  public static void main(String[] args) throws InterruptedException {
    Map<String, Integer> cricketTeamScore = new HashMap<>(4);
    cricketTeamScore.put("Australia", 349);
    cricketTeamScore.put("India", 250);

    // Create an ExecutorService with a Thread Pool of size 10
    ExecutorService executorService = new ThreadPoolExecutor(10, 10,
      0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), Executors.defaultThreadFactory());

    // Create a Runnable object that increments the value associated with a given key in the HashMap by one.
    Runnable task = () -> incrementTeamScore(cricketTeamScore, "India");

    // Submit the Runnable object to the executorService 100 times to test concurrent modifications
    for (int i = 0; i < 100; i++) {
      executorService.submit(task);
    }

    executorService.shutdown();
    executorService.awaitTermination(60, TimeUnit.SECONDS);

    System.out.println("Final Score of Team India : " + cricketTeamScore.get("India"));
  }

  /**
   * Increment the score of a team by one
   * @param cricketTeamScore
   * @param team
   */
  private static void incrementTeamScore(Map<String, Integer> cricketTeamScore, String team) {
    Integer score = cricketTeamScore.get(team);
    cricketTeamScore.put(team, score + 1);
  }
}
