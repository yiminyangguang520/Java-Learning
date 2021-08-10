package com.lee.dp.observer.example2;

/**
 * 观察者，比如报纸的读者
 * @author bruce
 */
public interface Observer {

  /**
   * 被通知的方法
   *
   * @param subject 具体的目标对象，可以获取报纸的内容
   */
  void update(Subject subject);
}
