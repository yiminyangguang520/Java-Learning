package com.shihuc.dbconn.service.mongo;

import com.shihuc.dbconn.dao.mongo.MongoUserRepository;
import com.shihuc.dbconn.pojo.mongo.MongoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 */
@Service("mongoUserService")
public class MongoUserService {

  @Autowired
  private MongoUserRepository mongoUserRepository;

  public void addUser(MongoUser user) {
    mongoUserRepository.addUser(user);
  }

  public MongoUser getUser(String username) {
    return mongoUserRepository.getUser(username);
  }
}
