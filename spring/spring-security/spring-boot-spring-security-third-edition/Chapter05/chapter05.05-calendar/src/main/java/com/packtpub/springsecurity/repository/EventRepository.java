package com.packtpub.springsecurity.repository;

import com.packtpub.springsecurity.domain.Event;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * @author litz-a
 */
public interface EventRepository extends MongoRepository<Event, Integer> {

  /**
   * findByUser
   * @param name
   * @return
   */
  @Query("{'owner.id' : ?0}")
  List<Event> findByUser(Integer name);

}
