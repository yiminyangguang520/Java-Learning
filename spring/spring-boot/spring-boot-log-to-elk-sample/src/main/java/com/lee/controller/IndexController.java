package com.lee.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
public class IndexController {

  private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

  @GetMapping("/{name}")
  public String hi(@PathVariable(value = "name") String name) {
    logger.info("name = {}", name);
    return "hi , " + name;
  }
}
