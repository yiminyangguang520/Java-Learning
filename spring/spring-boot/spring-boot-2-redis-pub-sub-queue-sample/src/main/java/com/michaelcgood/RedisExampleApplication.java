package com.michaelcgood;

import com.michaelcgood.queue.publish.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author litz-a
 */
@SpringBootApplication
public class RedisExampleApplication implements CommandLineRunner {

  @Autowired
  private MessagePublisher redisPublisher;

  public static void main(String[] args) {
    SpringApplication.run(RedisExampleApplication.class, args);
  }

  /**
   * Callback used to run the bean.
   *
   * @param args incoming main method arguments
   * @throws Exception on error
   */
  @Override
  public void run(String... args) throws Exception {
    redisPublisher.publish("1");
    redisPublisher.publish("2");
    redisPublisher.publish("3");
    Thread.sleep(50);
    redisPublisher.publish("4");
    redisPublisher.publish("5");
  }
}
