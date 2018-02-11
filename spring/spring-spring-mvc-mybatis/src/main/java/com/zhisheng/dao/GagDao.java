package com.zhisheng.dao;

import com.zhisheng.model.Gag;
import java.util.List;

/**
 * Created by 10412 on 2017/8/9.
 */
public interface GagDao {

  /**
   * 插入禁言记录
   */
  int insertGag(Gag gag);

  /**
   *
   * @param id
   * @return
   */
  List<Gag> selectGagByUserId(long id);

}
