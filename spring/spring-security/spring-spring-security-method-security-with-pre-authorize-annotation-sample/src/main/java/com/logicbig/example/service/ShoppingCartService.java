package com.logicbig.example.service;

import com.logicbig.example.model.OrderItem;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author litz-a
 */
public interface ShoppingCartService {

  /**
   * placeOrder
   * @param order
   * @return
   */
  @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
  int placeOrder(OrderItem order);

  /**
   * getOrderList
   * @return
   */
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  List<OrderItem> getOrderList();
}