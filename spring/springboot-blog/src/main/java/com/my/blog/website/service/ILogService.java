package com.my.blog.website.service;

import com.my.blog.website.modal.vo.LogVo;
import java.util.List;

/**
 * Created by BlueT on 2017/3/4.
 */
public interface ILogService {

  /**
   * 保存操作日志
   */
  void insertLog(LogVo logVo);

  /**
   * 保存
   */
  void insertLog(String action, String data, String ip, Integer authorId);

  /**
   * 获取日志分页
   *
   * @param page 当前页
   * @param limit 每页条数
   * @return 日志
   */
  List<LogVo> getLogs(int page, int limit);
}
