package com.javadeveloperzone.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JavaDeveloperZone
 * @date 19-07-2017
 */
@RestController
public class HelloController {

  @RequestMapping(value = "/hello-filter-header")
  public String hello() {
    return "Spring Boot Example";
  }

  @GetMapping(value = "/hello-entity-headers")
  public ResponseEntity helloEntityHeaders() {
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("Custom-Entity-Header-", "Write Header Using ResponseEntity");
    ResponseEntity responseEntity = new ResponseEntity("Spring boot Custom header Example", responseHeaders, HttpStatus.OK);
    return responseEntity;
  }

  @GetMapping(value = "/hello-response-headers")
  public String helloResponseHeaders(HttpServletResponse response) {
    response.addHeader("Custom-Response-Headers", "Write header using HttpServletResponse in Controllers");
    return "Spring boot Custom header Example";
  }

}
