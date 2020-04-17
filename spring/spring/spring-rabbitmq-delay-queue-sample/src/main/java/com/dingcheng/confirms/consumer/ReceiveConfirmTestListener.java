package com.dingcheng.confirms.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Service("receiveConfirmTestListener")
public class ReceiveConfirmTestListener implements ChannelAwareMessageListener {


  @Override
  public void onMessage(Message message, Channel channel) throws Exception {
    try {
      System.out.println("consumer--:" + message.getMessageProperties() + ":" + new String(message.getBody()));
      channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    } catch (Exception e) {
      e.printStackTrace();//TODO 业务处理
      channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
    }
  }

}  
