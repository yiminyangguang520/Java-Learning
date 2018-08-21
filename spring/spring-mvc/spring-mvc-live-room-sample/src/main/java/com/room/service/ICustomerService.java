package com.room.service;

import com.room.entity.Customer;
import com.room.entity.StatusMessage;
import java.util.List;

/**
 * @author litz-a
 * Created by Doublestar on 2017/12/5 21:06).
 */
public interface ICustomerService {

  /**
   * 获得所有客户信息
   */
  public List<Customer> getAllCustomer();

  /**
   * 查找客户
   */
  public List<Customer> findCustomer(Customer customer);

  /**
   * 根据ID得到客户信息
   */
  public Customer getCustomerBeforeChange(int id);

  /**
   * 更新客户信息
   */
  public boolean updateCustomer(int id, Customer customer);

  /**
   * 添加客户信息
   */
  public StatusMessage insertCustomer(Customer customer);

  /**
   * 删除客户信息
   */
  public boolean deleteCustomer(int id);
}
