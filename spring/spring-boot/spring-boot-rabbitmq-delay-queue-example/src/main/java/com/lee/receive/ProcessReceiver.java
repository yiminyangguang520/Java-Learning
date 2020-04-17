package com.lee.receive;

import com.lee.config.QueueConfig;
import com.rabbitmq.client.Channel;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @author min
 */
@Component
public class ProcessReceiver implements ChannelAwareMessageListener {

  private static Logger logger = LoggerFactory.getLogger(ProcessReceiver.class);

  public static final String FAIL_MESSAGE = "This message will fail";

  public static CountDownLatch latch;

  @Override
  public void onMessage(Message message, Channel channel) throws Exception {
    try {
      processMessage(message);
    } catch (Exception e) {
      // 如果发生了异常，则将该消息重定向到缓冲队列，会在一定延迟之后自动重做
      channel.basicPublish(QueueConfig.PER_QUEUE_TTL_EXCHANGE_NAME, QueueConfig.DELAY_QUEUE_PER_QUEUE_TTL_NAME, null,
          "The failed message will auto retry after a certain delay".getBytes());
    }

    if (latch != null) {
      latch.countDown();
    }
  }

  /**
   * 模拟消息处理。如果当消息内容为FAIL_MESSAGE的话，则需要抛出异常
   */
  public void processMessage(Message message) throws Exception {
    String realMessage = new String(message.getBody());
    logger.info("Received <" + realMessage + ">");
    if (Objects.equals(realMessage, FAIL_MESSAGE)) {
      throw new Exception("Some exception happened");
    }
  }
}
