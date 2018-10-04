package com.octoperf.controller;

import static com.google.common.base.Strings.emptyToNull;
import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

import com.octoperf.service.UserAuthenticationService;
import com.octoperf.service.UserRegistrationService;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/users")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
final class PublicUsersController {

  @NonNull
  UserAuthenticationService authentication;

  @NonNull
  UserRegistrationService registration;

  @PostMapping("/register")
  String register(final HttpServletRequest request,
      @RequestParam("username") final String username,
      @RequestParam(value = "password", required = false) final String password) {
    registration.register(username, ofNullable(emptyToNull(password)));
    return authentication.login(username, password).orElseThrow(RuntimeException::new);
  }

  @PostMapping("/login")
  String login(final HttpServletRequest request,
      @RequestParam("username") final String username,
      @RequestParam("password") final String password) {
    return authentication
        .login(username, password)
        .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
  }
}