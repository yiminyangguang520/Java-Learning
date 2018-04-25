package com.memorynotfound.service;

import com.memorynotfound.model.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 */
@Service
public class UserServiceImpl implements UserService {

  private static final AtomicInteger counter = new AtomicInteger();
  static List<User> users = new ArrayList<>(
      Arrays.asList(
          new User(counter.incrementAndGet(), "Daenerys Targaryen"),
          new User(counter.incrementAndGet(), "John Snow"),
          new User(counter.incrementAndGet(), "Arya Stark"),
          new User(counter.incrementAndGet(), "Cersei Baratheon")));

  @Override
  public List<User> getAll(int offset, int count) {
    return users;
  }

  @Override
  public User findById(int id) {
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
      if (user.getUsername().equals(name)) {
        return user;
      }
    }
    return null;
  }

  @Override
  public void create(User user) {
    user.setId(counter.incrementAndGet());
    users.add(user);
  }

  @Override
  public void update(User user) {
    int index = users.indexOf(user);
    users.set(index, user);
  }

  @Override
  public void delete(int id) {
    User user = findById(id);
    users.remove(user);
  }

  @Override
  public boolean exists(User user) {
    return findByName(user.getUsername()) != null;
  }
}
