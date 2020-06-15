package com.marcosbarbero.lab.sec.oauth.jwt.ds.web;

import java.security.Principal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
@RequestMapping("/me")
public class UserController {

  @GetMapping
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<Principal> get(final Principal principal) {
    return ResponseEntity.ok(principal);
  }

}