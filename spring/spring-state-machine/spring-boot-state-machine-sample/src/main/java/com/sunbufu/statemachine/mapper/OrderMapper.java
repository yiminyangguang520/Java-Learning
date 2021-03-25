package com.sunbufu.statemachine.mapper;

import com.sunbufu.statemachine.entity.Order;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * order数据库操作
 *
 * @author sunbufu
 */
@Component
public class OrderMapper {

  private static final Map<Integer, Order> DB = new HashMap<>();

  private static int id = 1;

  /**
   * 保存
   *
   * @param order 订单
   * @return 订单
   */
  public Order save(Order order) {
    if (null == order.getId()) {
      order.setId(id++);
    }
    DB.put(order.getId(), order);
    return order;
  }

  /**
   * 查询
   *
   * @param id 订单id
   * @return 订单
   */
  public Order select(int id) {
    return DB.get(id);
  }

}
