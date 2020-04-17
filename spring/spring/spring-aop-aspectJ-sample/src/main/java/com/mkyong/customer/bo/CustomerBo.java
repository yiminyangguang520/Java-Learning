package com.mkyong.customer.bo;

/**
 * @author min
 */
public interface CustomerBo {

  /**
   * add customer
   */
  void addCustomer();

  /**
   * add customer after
   */
  void addCustomerAfter();

  /**
   * addCustomerReturnValue
   * @return
   */
  String addCustomerReturnValue();

  /**
   * addCustomerThrowException
   * @throws Exception
   */
  void addCustomerThrowException() throws Exception;

  /**
   * addCustomerAround
   * @param name
   */
  void addCustomerAround(String name);
}