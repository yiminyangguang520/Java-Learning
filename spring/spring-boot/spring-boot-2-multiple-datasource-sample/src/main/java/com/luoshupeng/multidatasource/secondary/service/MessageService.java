package com.luoshupeng.multidatasource.secondary.service;

import com.luoshupeng.multidatasource.baseservice.IBaseService;
import com.luoshupeng.multidatasource.secondary.entity.Message;
import com.luoshupeng.multidatasource.secondary.repository.MessageRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 * Created by luoshupeng on 2018-03-20 10:27
 */
@Service
public class MessageService implements IBaseService<Message> {

  @Autowired
  private MessageRepository messageRepository;

  @Override
  public List<Message> list() {
    return messageRepository.findAll();
  }
}
