package com.octoperf.controller;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

import com.octoperf.security.User;
import com.octoperf.service.UserAuthenticationService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
final class SecuredUsersController {

  @NonNull
  UserAuthenticationService authentication;

  @GetMapping("/current")
  User getCurrent(@AuthenticationPrincipal final User user) {
    return user;
  }

  @GetMapping("/logout")
  boolean logout(@AuthenticationPrincipal final User user) {
    authentication.logout(user);
    return true;
  }
}