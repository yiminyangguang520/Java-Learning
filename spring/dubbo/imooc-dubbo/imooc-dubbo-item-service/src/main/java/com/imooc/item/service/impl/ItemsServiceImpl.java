package com.imooc.item.service.impl;

import com.imooc.item.mapper.ItemsMapper;
import com.imooc.item.pojo.Items;
import com.imooc.item.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 */
@Service("itemsService")
public class ItemsServiceImpl implements ItemsService {

  @Autowired
  private ItemsMapper itemsMapper;

  @Override
  public Items getItem(String itemId) {
    return itemsMapper.selectByPrimaryKey(itemId);
  }

  @Override
  public int getItemCounts(String itemId) {
    Items item = itemsMapper.selectByPrimaryKey(itemId);
    return item.getCounts();
  }

  @Override
  public void displayReduceCounts(String itemId, int buyCounts) {
    Items reduceItem = new Items();
    reduceItem.setId(itemId);
    reduceItem.setBuyCounts(buyCounts);
    itemsMapper.reduceCounts(reduceItem);
  }

}

