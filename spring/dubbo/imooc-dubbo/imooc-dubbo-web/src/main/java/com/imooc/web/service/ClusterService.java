package com.imooc.web.service;

/**
 * @author litz-a
 */
public interface ClusterService {

  /**
   * 购买商品
   * @param itemId
   */
  void doBuyItem(String itemId);

  /**
   * 显示购买
   * @param itemId
   * @return
   */
  boolean displayBuy(String itemId);
}

