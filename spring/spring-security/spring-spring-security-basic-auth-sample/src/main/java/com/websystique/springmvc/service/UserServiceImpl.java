package com.websystique.springmvc.service;

import com.websystique.springmvc.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

/**
 * @author bruce
 */
@Service("userService")
public class UserServiceImpl implements UserService {

  private static final AtomicLong COUNTER = new AtomicLong();

  private static List<User> users;

  static {
    users = populateDummyUsers();
  }

  @Override
  public List<User> findAllUsers() {
    return users;
  }

  @Override
  public User findById(long id) {
    for (User user : users) {
      if (user.getId() == id) {
        return user;
      }
    }
    return null;
  }

  @Override
  public User findByName(String name) {
    for (User user : users) {
      if (user.getName().equalsIgnoreCase(name)) {
        return user;
      }
    }
    return null;
  }

  @Override
  public void saveUser(User user) {
    user.setId(COUNTER.incrementAndGet());
    users.add(user);
  }

  @Override
  public void updateUser(User user) {
    int index = users.indexOf(user);
    users.set(index, user);
  }

  @Override
  public void deleteUserById(long id) {
    users.removeIf(user -> user.getId() == id);
  }

  @Override
  public boolean isUserExist(User user) {
    return findByName(user.getName()) != null;
  }

  @Override
  public void deleteAllUsers() {
    users.clear();
  }

  private static List<User> populateDummyUsers() {
    List<User> users = new ArrayList<>();
    users.add(new User(COUNTER.incrementAndGet(), "Sam", 30, 70000));
    users.add(new User(COUNTER.incrementAndGet(), "Tom", 40, 50000));
    users.add(new User(COUNTER.incrementAndGet(), "Jerome", 45, 30000));
    users.add(new User(COUNTER.incrementAndGet(), "Silvia", 50, 40000));
    return users;
  }

}
