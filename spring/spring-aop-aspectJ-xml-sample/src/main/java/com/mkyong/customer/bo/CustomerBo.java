package com.mkyong.customer.bo;

/**
 * @author litz-a
 */
public interface CustomerBo {

  /**
   * addCustomer
   */
  void addCustomer();

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