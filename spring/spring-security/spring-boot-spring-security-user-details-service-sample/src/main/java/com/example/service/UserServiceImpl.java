package com.example.service;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author litz-a
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

  @Autowired
  @Qualifier("userRepository")
  private UserRepository userRepository;

  @Autowired
  @Qualifier("roleRepository")
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public User findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public void saveUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setActive(true);

    // 采用h2数据库时,需要在role表中首先创建一条ADMIN记录,为了避免启动后手动创建,故在注册用户时,临时创建一条ADMIN记录
    Role role = new Role();
    role.setRole("ADMIN");
    roleRepository.save(role);

    Role userRole = roleRepository.findByRole("ADMIN");
    user.setRoles(new HashSet<>(Arrays.asList(userRole)));
    userRepository.save(user);
  }

  @Override
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(userName);
    List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
    return buildUserForAuthentication(user, authorities);
  }

  private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
    Set<GrantedAuthority> roles = new HashSet<>();
    for (Role role : userRoles) {
      roles.add(new SimpleGrantedAuthority(role.getRole()));
    }

    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
    return grantedAuthorities;
  }

  private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isActive(), true, true, true, authorities);
  }
}