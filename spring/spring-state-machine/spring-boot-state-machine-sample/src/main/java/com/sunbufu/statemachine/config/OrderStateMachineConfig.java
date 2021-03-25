package com.sunbufu.statemachine.config;

import com.sunbufu.statemachine.entity.Order;
import com.sunbufu.statemachine.entity.OrderEvent;
import com.sunbufu.statemachine.entity.OrderState;
import java.util.EnumSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

/**
 * 订单状态机配置
 *
 * @author sunbufu
 */
@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class OrderStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderState, OrderEvent> {

  @Override
  public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
    states.withStates()
        // 默认状态
        .initial(OrderState.WAIT_PAYMENT)
        // 全部状态
        .states(EnumSet.allOf(OrderState.class));
  }

  @Override
  public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
    transitions.withExternal()
        // 支付事件 待支付 -> 待发货
        .source(OrderState.WAIT_PAYMENT).target(OrderState.WAIT_DELIVER).event(OrderEvent.PAYED)
        // 发货事件 待发货 -> 待收货
        .and().withExternal().source(OrderState.WAIT_DELIVER).target(OrderState.WAIT_RECEIVE).event(OrderEvent.DELIVERY)
        // 收货事件 待收货 -> 完结
        .and().withExternal().source(OrderState.WAIT_RECEIVE).target(OrderState.FINISH).event(OrderEvent.RECEIVED);
  }

  /**
   * 状态机持久化
   */
  @Bean
  public StateMachinePersister<OrderState, OrderEvent, Order> orderStateMachinePersister() {
    return new DefaultStateMachinePersister<>(new StateMachinePersist<OrderState, OrderEvent, Order>() {
      @Override
      public void write(StateMachineContext<OrderState, OrderEvent> context, Order order) {
        // 进行持久化操作
        order.setStatus(context.getState());
      }

      @Override
      public StateMachineContext<OrderState, OrderEvent> read(Order order) {
        // 读取状态并设置到context中
        return new DefaultStateMachineContext<>(order.getStatus(), null, null, null);
      }
    });
  }
}
