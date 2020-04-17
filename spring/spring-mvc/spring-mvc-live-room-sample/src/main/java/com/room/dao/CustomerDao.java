package com.room.dao;

import com.room.entity.Customer;
import java.util.Date;
import java.util.List;


/**
 * @author min
 * DAO接口,mybatis动态完成Impl
 */
public interface CustomerDao {

  /**
   * 添加客户
   * @param customer
   * @return
   */
  int insertCustomer(Customer customer);

  /**
   * 删除客户
   * @param id
   * @return
   */
  int deleteCustomerById(int id);

  /**
   * 删除用户
   * @param name
   * @return
   */
  int deleteCustomerByName(String name);

  /**
   * 修改客户
   * @param customer
   * @return
   */
  int updateCustomer(Customer customer);

  /**
   * 查找所有用户
   * @return
   */
  List<Customer> getAllCustomer();

  /**
   * 根据ID查找客户相关信息
   * @param id
   * @return
   */
  Customer findCustomerById(int id);

  /**
   * 根据名字查找客户相关信息
   * @param name
   * @return
   */
  List<Customer> findCustomerByName(String name);

  /**
   * 根据行业查找客户相关信息
   * @param professtion
   * @return
   */
  List<Customer> findCustomerByProfession(String professtion);

  /**
   * 根据省份查找客户相关信息
   * @param province
   * @return
   */
  List<Customer> findCustomerByProvince(String province);

  /**
   * 根据注册时间查找客户相关信息
   * @param uptime
   * @return
   */
  List<Customer> findCustomerByUptime(Date uptime);

  /**
   * 根据资金查找客户相关信息
   * @param funds
   * @return
   */
  List<Customer> findCustomerByFunds(long funds);

  /**
   * 根据法人查找客户相关信息
   * @param corporation
   * @return
   */
  List<Customer> findCustomerByCorporation(String corporation);
}
