package org.spring.springboot.dao;

import org.spring.springboot.domain.Order;

import java.util.List;

/**
 * Created by litz-a on 2017/5/24.
 */
public interface OrderDao {
    List<Order> findAllOrders();
    long saveOrder(Order order);
}
