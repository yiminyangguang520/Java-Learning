package org.lee.controller;

import org.lee.kit.RequestBodyKit;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author min
 */
@RestController
public class IndexController {

  // curl -XPOST -d "name=lili&age=45" 'http://127.0.0.1:8080/info'
  @RequestMapping(path = "/info", method = {RequestMethod.POST})
  public String get(ServerWebExchange serverWebExchange) {
    ServerHttpRequest request = serverWebExchange.getRequest();
    String body = RequestBodyKit.resolveBodyFromRequest(request);
    //spring-boot-starter-parent 2.1.0.RELEASE + Spring Cloud Greenwich.M3 body always null
    //but in spring-boot-starter-parent 2.0.6.RELEASE + Spring Cloud Finchley.SR2 body is correct
    // i don`t know why?
    System.out.println("body is:" + body);
    return "ok";
  }
}
