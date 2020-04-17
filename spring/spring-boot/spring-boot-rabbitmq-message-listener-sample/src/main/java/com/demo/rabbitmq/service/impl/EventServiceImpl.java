package com.demo.rabbitmq.service.impl;

import com.demo.rabbitmq.configuration.QueueConfig;
import com.demo.rabbitmq.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Service
public class EventServiceImpl implements EventService {

  private final static Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Override
  public <T> Boolean sendEvent(T t) {
    LOGGER.info("Sending event through queue  = {} ", t);
    rabbitTemplate.convertAndSend(QueueConfig.TX_EVENT_QUEUE, t);

    return true;
  }

}
