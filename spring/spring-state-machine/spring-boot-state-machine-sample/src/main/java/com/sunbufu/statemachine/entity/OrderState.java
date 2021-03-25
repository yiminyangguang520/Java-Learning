package com.sunbufu.statemachine.entity;

/**
 * 订单状态
 *
 * @author sunbufu
 */
public enum OrderState {
  /**
   * 待支付
   */
  WAIT_PAYMENT,
  /**
   * 待发货
   */
  WAIT_DELIVER,
  /**
   * 待收货
   */
  WAIT_RECEIVE,
  /**
   * 完结
   */
  FINISH;
}
