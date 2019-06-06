package com.xingfly.account.controller;

import java.security.Principal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by SuperS on 2017/9/25.
 *
 * @author SuperS
 */
@RestController
public class AccountController {

  @GetMapping("/current")
  public Principal user(Principal principal) {
    return principal;
  }

  @GetMapping("/query")
  @PreAuthorize("hasAnyAuthority('query')")
  public String query() {
    return "具有query权限";
  }
}
