package cn.com.hellowood.rocketmq.service;

import cn.com.hellowood.rocketmq.common.RocketMQServiceConstant;
import cn.com.hellowood.rocketmq.model.ProducerMessage;
import cn.com.hellowood.rocketmq.util.ProducerHelper;
import cn.com.hellowood.rocketmq.util.RocketMQServiceUtil;
import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import java.text.ParseException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HelloWood
 * @create 2017-08-28 17:07
 * @email hellowoodes@outlook.com
 **/

@Service
public class ProducerService {

  private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

  @Autowired
  ProducerHelper producerHelper;

  /**
   * Send message
   */
  public SendResult sendMessage(ProducerMessage producerMessage) {
    SendResult sendResult = null;
    try {
      // Generate message object
      Message message = generateMessage(producerMessage);

      // Send message
      sendResult = sendMessage(message, producerMessage);
    } catch (ParseException e) {
      logger.error("Send message failed, parse Data error ：" + e.getMessage());
      e.printStackTrace();
    }

    return sendResult;
  }

  /**
   * Send message in different method
   */
  private SendResult sendMessage(Message message, ProducerMessage producerMessage) {
    SendResult sendResult;

    // Make sure method is valid
    if (StringUtils.isEmpty(producerMessage.getMethod())) {
      producerMessage.setMethod(RocketMQServiceConstant.SYNCHRONOUS_MESSAGE);
    }

    switch (producerMessage.getMethod()) {
      // Send message in ASYNCHRONOUS method
      case RocketMQServiceConstant.ASYNCHRONOUS_MESSAGE:
        sendResult = producerHelper.sendAsynchronousMessage(message);
        break;
      // Send message in ONE_WAY method
      case RocketMQServiceConstant.ONE_WAY_MESSAGE:
        sendResult = producerHelper.sendOneWayMessage(message);
        break;
      // Send message in SYNCHRONOUS method
      case RocketMQServiceConstant.SYNCHRONOUS_MESSAGE:
      default:
        sendResult = producerHelper.sendSynchronousMessage(message);
        break;
    }
    return sendResult;
  }


  /**
   * Generate message object
   */
  private Message generateMessage(ProducerMessage producerMessage) throws ParseException {
    Message msg = new Message();
    msg.setTag(producerMessage.getTags());
    msg.setKey(producerMessage.getKey());
    msg.setTopic(producerMessage.getTopic());
    msg.setBody(JSON.toJSONString(producerMessage.getBody()).getBytes());

    // make sure message type is valid
    if (StringUtils.isEmpty(producerMessage.getType())) {
      producerMessage.setType(RocketMQServiceConstant.ORDER_MESSAGE);
    }

    switch (producerMessage.getType()) {
      // Generate Delay message
      case RocketMQServiceConstant.DELAY_MESSAGE:
        msg.setStartDeliverTime(System.currentTimeMillis() + producerMessage.getDelayTime());
        break;
      // Generate Timing message
      case RocketMQServiceConstant.TIMING_MESSAGE:
        long startDeliverTime = RocketMQServiceUtil.getTimestampByDateString(producerMessage.getStartDeliveryTime());
        // if start deliver time early than now, send message right now
        if (startDeliverTime < System.currentTimeMillis()) {
          startDeliverTime = System.currentTimeMillis();
        }
        msg.setStartDeliverTime(startDeliverTime);
        break;
      // Generate Order message
      case RocketMQServiceConstant.ORDER_MESSAGE:
      default:
        break;
    }

    return msg;
  }
}
