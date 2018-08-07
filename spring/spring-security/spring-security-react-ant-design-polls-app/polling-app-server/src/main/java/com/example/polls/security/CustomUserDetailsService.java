package com.example.polls.security;

import com.example.polls.exception.ResourceNotFoundException;
import com.example.polls.model.User;
import com.example.polls.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rajeevkumarsingh
 * @date 02/08/17
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public UserDetails loadUserByUsername(String usernameOrEmail)
      throws UsernameNotFoundException {
    // Let people login with either username or email
    User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
        .orElseThrow(() ->
            new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
        );

    return UserPrincipal.create(user);
  }

  @Transactional(rollbackFor = Exception.class)
  public UserDetails loadUserById(Long id) {
    User user = userRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("User", "id", id)
    );

    return UserPrincipal.create(user);
  }
}