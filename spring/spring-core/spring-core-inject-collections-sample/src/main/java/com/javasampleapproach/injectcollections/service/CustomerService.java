package com.javasampleapproach.injectcollections.service;

import com.javasampleapproach.injectcollections.model.Customer;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author min
 */
public class CustomerService {

  Set<String> serviceSet;
  Map<Integer, String> channelServiceMap;
  List<Customer> customerList;
  Properties language;

  public Set<String> getServiceSet() {
    return serviceSet;
  }

  public void setServiceSet(Set<String> serviceSet) {
    this.serviceSet = serviceSet;
  }

  public Map<Integer, String> getChannelServiceMap() {
    return channelServiceMap;
  }

  public void setChannelServiceMap(Map<Integer, String> channelServiceMap) {
    this.channelServiceMap = channelServiceMap;
  }

  public List<Customer> getCustomerList() {
    return customerList;
  }

  public void setCustomerList(List<Customer> customerList) {
    this.customerList = customerList;
  }

  public Properties getLanguage() {
    return language;
  }

  public void setLanguage(Properties language) {
    this.language = language;
  }

}
