package ns.client;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author min
 */
@SpringBootApplication

public class ClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClientApplication.class, args);
  }

  @Bean
  ApplicationRunner init(TestClass testClass) {
    return (ApplicationArguments args) -> dataSetup(testClass);
  }

  private void dataSetup(TestClass testClass) throws InterruptedException {
    testClass.run();
  }

}
