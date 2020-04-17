package com.journaldev.bootifulmongodb.service;

import com.journaldev.bootifulmongodb.model.User;
import com.journaldev.bootifulmongodb.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author min
 */
@Repository
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

  /**
   * getAllUsers
   */
  @Override
  public List<User> getAllUsersByExample() {
    ExampleMatcher matcher = ExampleMatcher.matching()
      .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.exact())
      .withMatcher("userId", ExampleMatcher.GenericPropertyMatchers.exact());

    User user = new User();
    user.setName("王昭君");
    user.setUserId("13455677");

    return userRepository.findAll(Example.of(user, matcher) , new Sort(Sort.Direction.ASC, "name"));
  }

  @Override
  public List<User> getAllUsers() {
    return mongoTemplate.findAll(User.class);
  }

  @Override
  public User getUserById(String userId) {
    Query query = new Query();
    query.addCriteria(Criteria.where("userId").is(userId));
    return mongoTemplate.findOne(query, User.class);
  }

  @Override
  public User addNewUser(User user) {
    mongoTemplate.save(user);
    // Now, user object will contain the ID as well
    return user;
  }

  @Override
  public Object getAllUserSettings(String userId) {
    Query query = new Query();
    query.addCriteria(Criteria.where("userId").is(userId));
    User user = mongoTemplate.findOne(query, User.class);
    return user != null ? user.getUserSettings() : "User not found.";
  }

  @Override
  public String getUserSetting(String userId, String key) {
    Query query = new Query();
    query.fields().include("userSettings");
    query.addCriteria(Criteria.where("userId").is(userId).andOperator(Criteria.where("userSettings." + key).exists(true)));
    User user = mongoTemplate.findOne(query, User.class);
    return user != null ? user.getUserSettings().get(key) : "Not found.";
  }

  @Override
  public String addUserSetting(String userId, String key, String value) {
    Query query = new Query();
    query.addCriteria(Criteria.where("userId").is(userId));
    User user = mongoTemplate.findOne(query, User.class);
    if (user != null) {
      user.getUserSettings().put(key, value);
      mongoTemplate.save(user);
      return "Key added.";
    } else {
      return "User not found.";
    }
  }
}
