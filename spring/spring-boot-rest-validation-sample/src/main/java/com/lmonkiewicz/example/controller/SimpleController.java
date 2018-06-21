package com.lmonkiewicz.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lmonkiewicz
 * @date 17.03.2017
 */
@RestController
@RequestMapping("/simple")
public class SimpleController {

  @GetMapping("/hello")
  private String hello() {
    return "Hello World!";
  }

  @GetMapping("/hello/{name}")
  private String hello(@PathVariable(value = "name", required = true) String name) {
    return String.format("Hello %s!", name);
  }

  @GetMapping("/name")
  private ResponseEntity<?> queryPerson(@RequestParam(value = "query", required = false) String query) {
    if ("Stefan".equals(query)) {
      return ResponseEntity.ok("Stefan Stefanowsky");
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
