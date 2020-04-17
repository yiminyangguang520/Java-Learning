package com.wolf.mongodbit.service;

import com.wolf.mongodbit.entity.mongodb.Blacklist;

/**
 * @author min
 */
public interface BlacklistService {

  Blacklist findByuserid(String userId);
}
