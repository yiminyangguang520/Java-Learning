package net.codejava.service;

import net.codejava.config.Provider;
import net.codejava.model.User;
import net.codejava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Service
public class UserService {

  @Autowired
  private UserRepository repo;

  public void processOAuthPostLogin(String username) {
    User existUser = repo.getUserByUsername(username);

    if (existUser == null) {
      User newUser = new User();
      newUser.setUsername(username);
      newUser.setProvider(Provider.GOOGLE);
      newUser.setEnabled(true);

      repo.save(newUser);

      System.out.println("Created new user: " + username);
    }

  }

}
