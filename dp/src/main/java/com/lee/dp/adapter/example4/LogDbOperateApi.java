package com.lee.dp.adapter.example4;

import java.util.*;

/**
 * 定义操作日志的应用接口，为了示例的简单， 只是简单的定义了增删改查的方法
 * @author bruce
 */
public interface LogDbOperateApi {

  /**
   * 新增日志
   *
   * @param lm 需要新增的日志对象
   */
  void createLog(LogModel lm);

  /**
   * 修改日志
   *
   * @param lm 需要修改的日志对象
   */
  void updateLog(LogModel lm);

  /**
   * 删除日志
   *
   * @param lm 需要删除的日志对象
   */
  void removeLog(LogModel lm);

  /**
   * 获取所有的日志
   *
   * @return 所有的日志对象
   */
  List<LogModel> getAllLog();

  /**
   * 删除所有
   */
  void removeAll();
}
