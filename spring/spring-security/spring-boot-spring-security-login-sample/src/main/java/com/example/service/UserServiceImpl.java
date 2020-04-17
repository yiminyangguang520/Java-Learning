package com.example.service;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import java.util.Arrays;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Service("userService")
public class UserServiceImpl implements UserService {

  @Autowired
  @Qualifier("userRepository")
  private UserRepository userRepository;

  @Autowired
  @Qualifier("roleRepository")
  private RoleRepository roleRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public User findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public void saveUser(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setActive(1);

    // 采用h2数据库时,需要在role表中首先创建一条ADMIN记录,为了避免启动后手动创建,故在注册用户时,临时创建一条ADMIN记录
    Role role = new Role();
    role.setRole("ADMIN");
    roleRepository.save(role);

    Role userRole = roleRepository.findByRole("ADMIN");
    user.setRoles(new HashSet<>(Arrays.asList(userRole)));
    userRepository.save(user);
  }

}
