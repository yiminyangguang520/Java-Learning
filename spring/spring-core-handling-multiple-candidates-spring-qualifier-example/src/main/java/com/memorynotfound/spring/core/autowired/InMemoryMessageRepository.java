package com.memorynotfound.spring.core.autowired;

import org.springframework.stereotype.Repository;

/**
 * @author litz-a
 */
@Repository
public class InMemoryMessageRepository implements MessageRepository {

  public void save() {
    System.out.println("saving in memory");
  }
}
