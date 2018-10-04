package com.octoperf.service.impl;

import com.octoperf.security.User;
import com.octoperf.service.UserRegistrationService;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
final class NoopRegistrationService implements UserRegistrationService {

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public User register(final String username, final Optional<String> password) {
    final String token = UUID.randomUUID().toString();
    return User
        .builder()
        .id(token)
        .username(username)
        .password(password.orElse("12345"))
        .build();
  }
}
