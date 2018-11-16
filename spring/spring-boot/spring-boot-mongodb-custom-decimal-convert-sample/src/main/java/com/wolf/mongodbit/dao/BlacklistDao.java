package com.wolf.mongodbit.dao;

import com.wolf.mongodbit.entity.mongodb.Blacklist;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository方式
 */
public interface BlacklistDao extends MongoRepository<Blacklist, String> {

  Blacklist findByuserid(String userId);
}
