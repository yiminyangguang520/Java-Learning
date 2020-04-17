package com.wolf.mongodbit.service.impl;

import com.wolf.mongodbit.dao.BlacklistDao;
import com.wolf.mongodbit.entity.mongodb.Blacklist;
import com.wolf.mongodbit.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Service
public class BlacklistServiceImpl implements BlacklistService {

  @Autowired
  private BlacklistDao blacklistDao;

  @Override
  public Blacklist findByuserid(String userId) {
    return blacklistDao.findByuserid(userId);
  }

}
