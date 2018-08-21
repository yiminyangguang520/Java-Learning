package com.javasampleapproach.springioc.service;

import com.javasampleapproach.springioc.bean.CustomerPackage;

/**
 * @author litz-a
 */
public class CustomerService {

  private CustomerPackage customerPackage;

  /**
   * setter injection
   * @param customerPackage 自定义包接口
   */
  public void setCustomerPackage(CustomerPackage customerPackage) {
    this.customerPackage = customerPackage;
  }

  public String callSupport() {
    return customerPackage.support();
  }
}
