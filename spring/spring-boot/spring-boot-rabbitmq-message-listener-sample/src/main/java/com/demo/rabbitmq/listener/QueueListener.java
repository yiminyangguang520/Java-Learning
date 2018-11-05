package com.demo.rabbitmq.listener;

import com.demo.rabbitmq.dto.TxEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
@Component
public class QueueListener {

  private final static Logger LOGGER = LoggerFactory.getLogger(QueueListener.class);

  public <T> void receiveMessage(T t) {
    LOGGER.info("Received <" + t + ">");
    if (t instanceof TxEvent) {
      System.out.println(((TxEvent) t).getAmount());
      System.out.println(((TxEvent) t).getCardId());
      System.out.println(((TxEvent) t).getTxRefNum());
      System.out.println(((TxEvent) t).isDebit());
    }
  }
}
