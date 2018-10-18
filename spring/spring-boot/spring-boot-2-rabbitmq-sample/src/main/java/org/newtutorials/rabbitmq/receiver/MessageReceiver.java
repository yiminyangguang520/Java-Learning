package org.newtutorials.rabbitmq.receiver;

import org.newtutorials.rabbitmq.message.SimpleMessage;
import org.springframework.stereotype.Component;

/**
 * Created by dani on 5/22/2017.
 *
 * @author litz-a
 */
@Component
public class MessageReceiver {

  private String queueName;

  public MessageReceiver() {
  }

  public MessageReceiver(String queueName) {
    this.queueName = queueName;
  }

  public void receiveMessage(SimpleMessage simpleMessage) {
    System.out.println("received[" + queueName + "] : " + simpleMessage);
  }
}
