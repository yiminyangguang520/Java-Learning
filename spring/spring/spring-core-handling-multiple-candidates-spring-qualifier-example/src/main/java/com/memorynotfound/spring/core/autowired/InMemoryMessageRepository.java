package com.memorynotfound.spring.core.autowired;

import org.springframework.stereotype.Repository;

/**
 * @author min
 */
@Repository
public class InMemoryMessageRepository implements MessageRepository {

  public void save() {
    System.out.println("saving in memory");
  }
}
