package com.sunbufu.statemachine.service.impl;

import static org.junit.Assert.assertTrue;

import com.sunbufu.statemachine.Application;
import com.sunbufu.statemachine.entity.Order;
import com.sunbufu.statemachine.entity.OrderState;
import com.sunbufu.statemachine.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderServiceImplTest {

  @Autowired
  private OrderService orderService;

  @Test
  public void testSuccess() {
    Order order = orderService.create();
    orderService.pay(order.getId());
    orderService.deliver(order.getId());
    orderService.receive(order.getId());
    assertTrue(OrderState.FINISH == order.getStatus());
  }

  @Test(expected = RuntimeException.class)
  public void testError() {
    Order order = orderService.create();
//        orderService.pay(order.getId());
    orderService.deliver(order.getId());
    orderService.receive(order.getId());
    assertTrue(OrderState.FINISH == order.getStatus());
  }

}