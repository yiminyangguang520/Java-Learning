package security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.model.Health;

/**
 * @author litz-a
 */
@RestController
public class HealthResource {

  @RequestMapping("/api/health")
  public Health health() {
    return new Health("Spring Boot is up and running!");
  }
}
