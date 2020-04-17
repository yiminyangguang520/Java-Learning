package com.logicbig.example.service;

import com.logicbig.example.model.OrderItem;
import java.util.List;
import org.springframework.security.access.annotation.Secured;

/**
 * @author min
 */
public interface ShoppingCartService {

  /**
   * placeOrder
   * @param order
   * @return
   */
  @Secured("ROLE_CUSTOMER")
  int placeOrder(OrderItem order);

  /**
   * getOrderList
   * @return
   */
  @Secured("ROLE_ADMIN")
  List<OrderItem> getOrderList();
}