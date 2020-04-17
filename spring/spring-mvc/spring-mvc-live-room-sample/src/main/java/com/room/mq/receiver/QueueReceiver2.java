package com.room.mq.receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.apache.activemq.command.ActiveMQBytesMessage;
import org.springframework.stereotype.Component;

/**
 * @author min
 * Created by Administrator on 2017/3/27 0027.
 */
@Component("queueReceiver2")
public class QueueReceiver2 implements MessageListener {

  @Override
  public void onMessage(Message message) {
    try {
      if (message instanceof ActiveMQBytesMessage) {
        System.out.println("QueueReceiver2接收到消息:" + ((ActiveMQBytesMessage) message).getContent().toString());
      } else {
        System.out.println("QueueReceiver2接收到消息:" + ((TextMessage) message).getText());
      }
      message.acknowledge();////手动向broker确认接收成功，如果发生异常，就不反回ack
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }
}