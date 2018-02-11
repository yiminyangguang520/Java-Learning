package com.zhisheng.service.impl;

import com.zhisheng.dao.GagDao;
import com.zhisheng.model.Gag;
import com.zhisheng.service.IGagService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 * Created by 10412 on 2017/8/9.
 */
@Service
public class GagServiceImpl implements IGagService {

  @Autowired
  private GagDao gagDao;

  @Override
  public int insertGag(Gag gag) {
    return gagDao.insertGag(gag);
  }

  @Override
  public List<Gag> selectGagByUserId(long id) {
    return gagDao.selectGagByUserId(id);
  }
}
