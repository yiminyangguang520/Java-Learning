package com.luoshupeng.multidatasource.baseservice;

import java.util.List;

/**
 * @author litz-a
 * Created by luoshupeng on 2018-03-20 10:25
 */
public interface IBaseService<T> {

  List<T> list();
}
