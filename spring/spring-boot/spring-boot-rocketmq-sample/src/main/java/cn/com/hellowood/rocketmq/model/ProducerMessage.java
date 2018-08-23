package cn.com.hellowood.rocketmq.model;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author HelloWood
 * @create 2017-08-28 17:04
 * @email hellowoodes@outlook.com
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProducerMessage<T> {

  private String topic;

  private String tags;

  private String key;

  /**
   * Send method ：SYNCHRONOUS ASYNCHRONOUS ONE_WAY
   */
  private String method;

  /**
   * Message type：Timing Order Delay
   */
  private String type;

  private long delayTime;

  private String startDeliveryTime;

  private T body;
}
