package com.lee.gateway.security;

import com.lee.gateway.bean.auth.MongoUserDetails;
import com.lee.gateway.bean.auth.Role;
import com.lee.gateway.bean.auth.User;
import com.lee.gateway.exception.CustomException;
import com.lee.gateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 */
@Service
public class UserServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email);
    if (user == null || user.getRole() == null || user.getRole().isEmpty()) {
      throw new CustomException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
    }
    String[] authorities = new String[user.getRole().size()];
    int count = 0;
    for (Role role : user.getRole()) {
      //NOTE: normally we dont need to add "ROLE_" prefix. Spring does automatically for us.
      //Since we are using custom token using JWT we should add ROLE_ prefix
      authorities[count] = "ROLE_" + role.getRole();
      count++;
    }
    MongoUserDetails userDetails = new MongoUserDetails(user.getEmail(), user.getPassword(), user.getActive(),
        user.isLoacked(), user.isExpired(), user.isEnabled(), authorities);
    return userDetails;
  }


}
