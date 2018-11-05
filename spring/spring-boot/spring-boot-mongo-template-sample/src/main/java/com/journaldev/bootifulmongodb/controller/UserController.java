package com.journaldev.bootifulmongodb.controller;

import com.journaldev.bootifulmongodb.service.UserService;
import com.journaldev.bootifulmongodb.repository.UserRepository;
import com.journaldev.bootifulmongodb.model.User;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
@RequestMapping(value = "/")
public class UserController {

  private final Logger LOG = LoggerFactory.getLogger(getClass());

  private final UserRepository userRepository;

  private final UserService userDAL;

  public UserController(UserRepository userRepository, UserService userDAL) {
    this.userRepository = userRepository;
    this.userDAL = userDAL;
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public User addNewUsers(@RequestBody User user) {
    LOG.info("Saving user.");
    return userRepository.save(user);
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<User> getAllUsers() {
    LOG.info("Getting all users.");
    return userRepository.findAll();
  }

  @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
  public Optional<User> getUser(@PathVariable String userId) {
    LOG.info("Getting user with ID: {}.", userId);
    return userRepository.findById(userId);
  }

  @RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
  public Object getAllUserSettings(@PathVariable String userId) {
    Optional<User> user = userRepository.findById(userId);
    if (user.isPresent()) {
      return userDAL.getAllUserSettings(userId);
    } else {
      return "User not found.";
    }
  }

  @RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
  public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
    return userDAL.getUserSetting(userId, key);
  }

  @RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
  public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
    Optional<User> user= userRepository.findById(userId);
    if (user.isPresent()) {
      user.get().getUserSettings().put(key, value);
      userRepository.save(user.get());
      return "Key added";
    } else {
      return "User not found.";
    }
  }
}