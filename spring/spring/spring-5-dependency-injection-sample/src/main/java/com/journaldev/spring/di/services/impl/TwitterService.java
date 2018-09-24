package com.journaldev.spring.di.services.impl;

import com.journaldev.spring.di.services.MessageService;

/**
 * @author litz-a
 */
public class TwitterService implements MessageService {

  public boolean sendMessage(String msg, String rec) {
    System.out.println("Twitter message Sent to " + rec + " with Message=" + msg);
    return true;
  }

}
