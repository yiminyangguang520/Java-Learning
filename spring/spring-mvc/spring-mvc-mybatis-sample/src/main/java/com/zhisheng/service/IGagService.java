package com.zhisheng.service;

import com.zhisheng.model.Gag;
import java.util.List;

/**
 * @author min
 * Created by 10412 on 2017/8/9.
 */
public interface IGagService {

  /**
   * 插入数据
   * @param gag
   * @return
   */
  int insertGag(Gag gag);

  /**
   * 根据用户ID查询
   * @param id
   * @return
   */
  List<Gag> selectGagByUserId(long id);
}
