package com.journaldev.spring.di.services;

/**
 * @author min
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
