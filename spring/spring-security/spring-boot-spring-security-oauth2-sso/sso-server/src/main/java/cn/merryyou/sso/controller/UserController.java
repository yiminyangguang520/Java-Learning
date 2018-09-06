package cn.merryyou.sso.controller;

import java.security.Principal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
public class UserController {

  @RequestMapping(value = "/user")
  public Principal user(Principal principal) {
    System.out.println(principal);
    return principal;
  }
}
