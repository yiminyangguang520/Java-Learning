package com.logicbig.example.controller;

import com.logicbig.example.model.OrderItem;
import com.logicbig.example.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author litz-a
 */
@Controller("/")
public class ShoppingCartController {

  @Autowired
  private ShoppingCartService shoppingCartService;

  @GetMapping
  public String placeOrderPage(Model model) {
    addUserInfo(model);
    return "place-order";
  }

  @RequestMapping("/noAccess")
  public String noAccess(Model model) {
    addUserInfo(model);
    return "no-access";
  }

  @RequestMapping(value = "placeOrder", method = RequestMethod.POST)
  public String addOrderItem(OrderItem orderItem, Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    orderItem.setCustomer(auth.getName());
    shoppingCartService.placeOrder(orderItem);
    model.addAttribute("status", "Order placed," + orderItem);
    addUserInfo(model);
    return "order-status";
  }

  @RequestMapping(value = "orders", method = RequestMethod.GET)
  public String getOrderItemList(Model model) {
    addUserInfo(model);
    model.addAttribute("orderList", shoppingCartService.getOrderList().toString());
    return "order-list";
  }

  private void addUserInfo(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    model.addAttribute("userInfo", String.format("%s [%s]", auth.getName(), auth.getAuthorities()));
  }
}