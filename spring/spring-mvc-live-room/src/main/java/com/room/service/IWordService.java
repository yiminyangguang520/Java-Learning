package com.room.service;

/**
 * @author litz-a
 * Created by Doublestar on 2017/12/27 23:49).
 */
public interface IWordService {

  /**
   * 过滤敏感词
   * @param str
   * @return
   */
  boolean filterWords(String str);
}
