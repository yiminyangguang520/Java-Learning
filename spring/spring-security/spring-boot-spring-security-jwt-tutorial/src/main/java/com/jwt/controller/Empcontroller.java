package com.jwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
public class Empcontroller {

  @GetMapping(value = "/welcome")
  public ResponseEntity<String> welcome() {
    return new ResponseEntity<>("Welcome User!", HttpStatus.OK);
  }
}