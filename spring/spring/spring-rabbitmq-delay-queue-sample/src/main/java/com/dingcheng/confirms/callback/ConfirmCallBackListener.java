package com.dingcheng.confirms.callback;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 */
@Service("confirmCallBackListener")
public class ConfirmCallBackListener implements ConfirmCallback {

  @Override
  public void confirm(CorrelationData correlationData, boolean ack, String cause) {
    System.out.println("confirm--:correlationData:" + correlationData + ",ack:" + ack + ",cause:" + cause);
  }
}
