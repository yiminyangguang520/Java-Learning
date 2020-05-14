package com.marcosbarbero.lab.sec.oauth.opaque.web;

import java.security.Principal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
@RequestMapping("/profile")
public class ProfileController {

  @GetMapping("/me")
  public ResponseEntity<Principal> get(final Principal principal) {
    return ResponseEntity.ok(principal);
  }

}
