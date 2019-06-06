package com.example.client.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
public class HelloController {

  @RequestMapping("/hi")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> hi() {
    return ResponseEntity.ok().body("auth success!");
  }

}
