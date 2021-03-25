package com.lijingyao.stateMachine;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author min
 */
public interface OrderRepo extends JpaRepository<Order, Integer> {

  Order findByOrderId(Integer order);
}
