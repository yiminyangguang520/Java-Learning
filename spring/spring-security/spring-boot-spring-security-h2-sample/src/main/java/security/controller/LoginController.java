package security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.model.LoginStatus;

/**
 * @author min
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {

  @RequestMapping
  public LoginStatus status(Authentication principal) {

    return principal == null ? new LoginStatus(false, null) : new LoginStatus(true, principal.getName());
  }
}
