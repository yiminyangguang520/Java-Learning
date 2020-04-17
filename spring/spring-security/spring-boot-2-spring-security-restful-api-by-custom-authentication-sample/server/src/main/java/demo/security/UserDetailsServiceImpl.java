package demo.security;

import demo.model.User;
import demo.repository.UserRepository;
import java.io.Serializable;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author min
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService, Serializable {

  @Autowired
  private UserRepository userRepository;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("username " + username + " not found");
    }
    Hibernate.initialize(user.getAuthorities());
    return user;
  }

}
