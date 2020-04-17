package com.example.tomcatconfig;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
public class IndexController {

  @GetMapping("/test")
  public String test(HttpServletRequest request) {
    int serverPort = request.getLocalPort();
    return "Server port is " + serverPort;
  }
}
