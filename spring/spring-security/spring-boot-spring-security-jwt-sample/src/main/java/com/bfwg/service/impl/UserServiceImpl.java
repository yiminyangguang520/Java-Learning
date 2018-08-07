package com.bfwg.service.impl;

import com.bfwg.model.User;
import com.bfwg.repository.UserRepository;
import com.bfwg.service.UserService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author fan.jin
 * @date 2016-10-15
 */

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public User findByUsername(String username) throws UsernameNotFoundException {
    User u = userRepository.findByUsername(username);
    return u;
  }

  @Override
  public User findById(Long id) throws AccessDeniedException {
    Optional<User> user = userRepository.findById(id);
    return user.get();
  }

  @Override
  public List<User> findAll() throws AccessDeniedException {
    List<User> result = userRepository.findAll();
    return result;
  }
}
