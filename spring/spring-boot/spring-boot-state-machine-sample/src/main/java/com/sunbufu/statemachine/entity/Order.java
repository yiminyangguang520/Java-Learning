package com.sunbufu.statemachine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单
 *
 * @author sunbufu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

  /**
   * 订单id
   */
  private Integer id;
  /**
   * 状态
   */
  private OrderState status;
}
