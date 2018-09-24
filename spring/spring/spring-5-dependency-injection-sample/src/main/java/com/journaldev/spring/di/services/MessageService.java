package com.journaldev.spring.di.services;

/**
 * @author litz-a
 */
public interface MessageService {

  /**
   * sendMessage
   * @param msg
   * @param rec
   * @return
   */
  boolean sendMessage(String msg, String rec);
}
