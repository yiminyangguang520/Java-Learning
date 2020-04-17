package com.room.service.impl;

import com.room.dao.CustomerDao;
import com.room.dao.LinkmanDao;
import com.room.entity.Customer;
import com.room.entity.StatusMessage;
import com.room.service.ICustomerService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author min
 * Created by Doublestar on 2017/12/5 21:06).
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerServiceImpl implements ICustomerService {

  @Autowired(required = false)
  CustomerDao customerDao;
  @Autowired(required = false)
  LinkmanDao linkmanDao;

  /**
   * 获得所有客户信息
   * @return
   */
  @Override
  public List<Customer> getAllCustomer() {
    return customerDao.getAllCustomer();
  }

  /**
   * 根据条件查找客户
   * @param customer
   * @return
   */
  @Override
  public List<Customer> findCustomer(Customer customer) {
    List<Customer> list = customerDao.findCustomerByName(customer.getName());
    list.addAll(customerDao.findCustomerByProfession(customer.getProfession()));
    list.addAll(customerDao.findCustomerByProvince(customer.getProvince()));
    list.addAll(customerDao.findCustomerByUptime(customer.getUptime()));
    list.addAll(customerDao.findCustomerByFunds(customer.getFunds()));
    list.addAll(customerDao.findCustomerByCorporation(customer.getCorporation()));
    List<Customer> customers = new ArrayList<>();
    // 对象List去重
    list.stream().forEach(
        c -> {
          if (!customers.contains(c)) {
            customers.add(c);
          }
        }
    );
    return customers;
  }

  /**
   * 根据Id查找客户
   * @param id
   * @return
   */
  @Override
  public Customer getCustomerBeforeChange(int id) {
    return customerDao.findCustomerById(id);
  }

  /**
   * 更新客户信息
   * @param id
   * @param customer
   * @return
   */
  @Override
  public boolean updateCustomer(int id, Customer customer) {
    customer.setId(id);
    return customerDao.updateCustomer(customer) > 0;
  }

  /**
   * 添加客户
   * @param customer
   * @return
   */
  @Override
  public StatusMessage insertCustomer(Customer customer) {
    if (customerDao.insertCustomer(customer) > 0) {
      int id = customer.getId();
      return new StatusMessage(200, "success", String.valueOf(id));
    } else {
      return new StatusMessage(500, "error", "客户添加失败！");
    }
  }

  /**
   * 删除客户，同时清空该客户关联的联系人信息
   * @param id
   * @return
   */
  @Override
  public boolean deleteCustomer(int id) {
//        if(customerDao.deleteCustomerById(id) > 0 && linkmanDao.deleteLinkmanByCid(id) > 0) {
    if (customerDao.deleteCustomerById(id) > 0) {
      return true;
    } else {
      return false;
    }
  }
}
