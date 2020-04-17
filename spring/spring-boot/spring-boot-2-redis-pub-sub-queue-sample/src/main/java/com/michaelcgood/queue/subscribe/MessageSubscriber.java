package com.michaelcgood.queue.subscribe;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Service
public class MessageSubscriber implements MessageListener {

  public static List<String> messageList = new ArrayList<>();

  @Override
  public void onMessage(final Message message, final byte[] pattern) {
    messageList.add(message.toString());
    System.out.println("Message received: " + new String(message.getBody()));
  }

}
