package com.lee.authlogin.service;

import com.lee.authlogin.domain.User;
import com.lee.authlogin.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    User user = userRepository.findByUserName(userName);
    if (user == null) {
      throw new UsernameNotFoundException("user + " + userName + "not found.");
    }
    
    List<String> roleCodeList = userRepository.queryUserOwnedRoleCodes(userName);

    List<GrantedAuthority> authorities = roleCodeList.stream()
        .map(e -> new SimpleGrantedAuthority(e))
        .collect(Collectors.toList());

    UserDetails userDetails = new org.springframework.security.core.userdetails.User(
        user.getUserName(), user.getPassword(), authorities);

    return userDetails;
  }
}
