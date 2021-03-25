package com.lijingyao.stateMachine;

import java.util.List;
import java.util.StringJoiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author min
 */
@Component
public class OrderStateService {

  private PersistStateMachineHandler handler;

  public OrderStateService(PersistStateMachineHandler handler) {
    this.handler = handler;
  }

  @Autowired
  private OrderRepo repo;


  public String listDbEntries() {
    List<Order> orders = repo.findAll();
    StringJoiner sj = new StringJoiner(",");
    for (Order order : orders) {
      sj.add(order.toString());
    }
    return sj.toString();
  }


  public boolean change(int order, OrderStatusChangeEvent event) {
    Order o = repo.findByOrderId(order);
    return handler.handleEventWithState(MessageBuilder.withPayload(event).setHeader("order", order).build(), o.getStatus());
  }


}
