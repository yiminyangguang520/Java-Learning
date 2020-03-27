package com.wanari.customlogin.example.service;

import com.wanari.customlogin.example.config.security.constant.Roles;
import com.wanari.customlogin.example.domain.User;
import com.wanari.customlogin.example.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author bruce
 */
@Service
public class UserServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @PostConstruct
  public void init() {
    User detonator = new User();
    detonator.setLogin("detonator");
    detonator.setPassword(passwordEncoder.encode("detonator"));
    detonator.setRoles(Roles.DETONATOR_ROLE);
    userRepository.save(detonator);

    User user = new User();
    user.setLogin("user");
    user.setPassword(passwordEncoder.encode("user"));
    user.setRoles(Roles.USER_ROLE);
    userRepository.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    User loggedInUser = findByLogin(login)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + login));

    List<SimpleGrantedAuthority> roles = Stream.of(loggedInUser.getRoles()).collect(Collectors.toList()).stream()
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());

    return new org.springframework.security.core.userdetails.User(loggedInUser.getLogin(), loggedInUser.getPassword(), roles);
  }

  public Optional<User> findByLogin(String login) {
    return userRepository.findByLogin(login);
  }
}
