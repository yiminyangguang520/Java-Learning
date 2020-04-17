package com.logicbig.example.service;

import com.logicbig.example.model.OrderItem;
import java.util.List;
import javax.annotation.security.RolesAllowed;

/**
 * @author min
 */
public interface ShoppingCartService {

  /**
   * placeOrder
   * @param order
   * @return
   */
  @RolesAllowed("ROLE_CUSTOMER")
  int placeOrder(OrderItem order);

  /**
   * getOrderList
   * @return
   */
  @RolesAllowed("ROLE_ADMIN")
  List<OrderItem> getOrderList();
}