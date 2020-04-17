package com.memorynotfound.spring.core.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author min
 */
@Component
public class SellPhone {

  @Autowired
  @Qualifier(value = "mysqlMessageRepository")
  private MessageRepository mysqlMessageRepository;

  @Autowired
  @Qualifier(value = "inMemoryMessageRepository")
  private MessageRepository inMemoryMessageRepository;

  public void sendMessage() {
    mysqlMessageRepository.save();
    inMemoryMessageRepository.save();
  }
}
