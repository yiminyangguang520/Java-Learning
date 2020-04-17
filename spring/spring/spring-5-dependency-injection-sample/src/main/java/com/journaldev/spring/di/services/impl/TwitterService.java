package com.journaldev.spring.di.services.impl;

import com.journaldev.spring.di.services.MessageService;

/**
 * @author min
 */
public class TwitterService implements MessageService {

  public boolean sendMessage(String msg, String rec) {
    System.out.println("Twitter message Sent to " + rec + " with Message=" + msg);
    return true;
  }

}
