package com.sunbufu.statemachine.entity;

/**
 * 订单事件
 *
 * @author sunbufu
 */
public enum OrderEvent {
  /**
   * 支付
   */
  PAYED,
  /**
   * 发货
   */
  DELIVERY,
  /**
   * 收货
   */
  RECEIVED;
}
